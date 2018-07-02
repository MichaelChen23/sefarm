package com.sefarm.controller.common;

import com.google.code.kaptcha.Constants;
import com.sefarm.common.Constant;
import com.sefarm.common.exception.InvalidKaptchaException;
import com.sefarm.common.node.MenuNode;
import com.sefarm.common.vo.SysUserVO;
import com.sefarm.config.properties.SeFarmProperties;
import com.sefarm.service.system.ISysMenuService;
import com.sefarm.service.system.ISysUserService;
import com.sefarm.util.ShiroUtil;
import com.sefarm.util.ToolUtil;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;


/**
 * 后台系统用户，管理员登录
 *
 * @author mc
 * @date 2018-3-30
 */
@Controller
public class LoginController extends BaseController {

//    @Reference(version = "1.0.0", timeout = Constant.DUBBO_TIME_OUT) 注解式导入dubbo服务与事务注解有冲突就不使用了。 modify by mc 2018-4-29

    @Resource
    private SeFarmProperties seFarmProperties;
    
    @Autowired
    public ISysUserService sysUserService;

    @Autowired
    public ISysMenuService sysMenuService;

    /**
     * 跳转到管理后台主页
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String index(Model model) {
        //获取用户信息
        SysUserVO sysUserVO = ShiroUtil.getSysUser();

        if (sysUserVO != null) {
            //判断用户角色
            Long roleId = sysUserVO.getSysRoleId();
            if(roleId == null || roleId <= 0){
                //退出登录
                ShiroUtil.getSubject().logout();
                model.addAttribute("tips", "该用户没有角色，无法登录");
                //跳转到登录界面
                return "/login.html";
            }
            //该用户给禁用
            if (!Constant.STATUS_UNLOCK.equals(sysUserVO.getStatus())) {
                //退出登录
                ShiroUtil.getSubject().logout();
                model.addAttribute("tips", "该用户给禁用，无法登录");
                //跳转到登录界面
                return "/login.html";
            }

            //保存当前操作人进session，并设置session有效时间
            getSession().setAttribute("username", sysUserVO.getUsername());
            getSession().setAttribute("rolename", sysUserVO.getSysRoleName());
            getSession().setMaxInactiveInterval(seFarmProperties.getSessionInvalidateTime());

            List<MenuNode> menus = sysMenuService.getMenusByRoleId(sysUserVO.getSysRoleId());
            List<MenuNode> titles = MenuNode.buildTitle(menus);
            model.addAttribute("titles", titles);

//
//        //获取用户头像
//        Integer id = ShiroKit.getUser().getId();
//        User user = userMapper.selectById(id);
//        String avatar = user.getAvatar();
//        model.addAttribute("avatar", avatar);
//
            return "/index.html";
        } else {
            return "/login.html";
        }

    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String login() {
        if (ShiroUtil.isAuthenticated() || ShiroUtil.getSysUser() != null) {
            return REDIRECT + "/admin";
        } else {
            return "/login.html";
        }
    }

    /**
     * 点击登录执行的动作
     */
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String loginVali() {

        String username = super.getPara("username").trim();
        String password = super.getPara("password").trim();

        //验证验证码是否正确
        if(ToolUtil.getKaptchaOnOff()){
            String kaptcha = super.getPara("kaptcha").trim();
            String code = (String) super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if(ToolUtil.isEmpty(kaptcha) || !kaptcha.equals(code)){
                throw new InvalidKaptchaException();
            }
        }
//
        Subject currentUser = ShiroUtil.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
//        token.setRememberMe(true);
        currentUser.login(token);

//        LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));
//
//        ShiroKit.getSession().setAttribute("sessionFlag",true);
//        return REDIRECT + "/";

        return REDIRECT + "/admin";
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
    public String logOut() {
//        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUser().getId(), getIp()));
        ShiroUtil.getSubject().logout();
        return REDIRECT + "/admin/login";
    }

    /**
     * 跳转到各种状态的错误页面
     * add by mc 2018-5-25
     * @param code
     * @return
     */
    @RequestMapping(value = "/error/{code}")
    public String error(@PathVariable int code) {
        return "/error/"+code+".html";
    }

}
