package com.sefarm.controller.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.code.kaptcha.Constants;
import com.sefarm.common.Constant;
import com.sefarm.common.exception.InvalidKaptchaException;
import com.sefarm.common.node.MenuNode;
import com.sefarm.model.system.SysUserDO;
import com.sefarm.service.system.ISysMenuService;
import com.sefarm.service.system.ISysUserService;
import com.sefarm.util.ToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;


/**
 * 后台系统用户，管理员登录
 *
 * @author mc
 * @date 2018-3-30
 */
@Controller
public class LoginController extends BaseController {

    @Reference(version = "1.0.0", timeout = 10000)
    public ISysUserService sysUserService;

    @Reference(version = "1.0.0", timeout = 10000)
    public ISysMenuService sysMenuService;

    /**
     * 跳转到主页
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String index(Model model) {
        //获取菜单列表
//        List<Integer> roleList = ShiroKit.getUser().getRoleList();
//        if(roleList == null || roleList.size() == 0){
//            ShiroKit.getSubject().logout();
//        model.addAttribute("tips", "该用户没有角色，无法登陆");
//        return "/login.html";
//        }

        List<MenuNode> menus = sysMenuService.getMenusByRoleId(88L);
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

    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String login() {
//        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
//            return REDIRECT + "/";
//        } else {
        return "/login.html";
//        }
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
//        Subject currentUser = ShiroKit.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
//        token.setRememberMe(true);
//
//        currentUser.login(token);
//
//        ShiroUser shiroUser = ShiroKit.getUser();
//        super.getSession().setAttribute("shiroUser", shiroUser);
//        super.getSession().setAttribute("username", shiroUser.getAccount());
//
//        LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));
//
//        ShiroKit.getSession().setAttribute("sessionFlag",true);
//        return REDIRECT + "/";


        SysUserDO userDO = null;
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            SysUserDO sysUserDO = new SysUserDO();
            sysUserDO.setUsername(username);
            sysUserDO.setPassword(password);
            userDO = sysUserService.getOneByObj(sysUserDO);
        }
        if (userDO != null && Constant.STATUS_UNLOCK.equals(userDO.getStatus())) {
//            userDO.setLastLoginTime(new Date());
//            //更新最新登录时间
//            sysUserService.updateByObj(userDO);
            return REDIRECT + "/admin";
        } else {
            return "/login.html";
        }

    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
    public String logOut() {
//        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUser().getId(), getIp()));
//        ShiroKit.getSubject().logout();
        return REDIRECT + "/admin/login";
    }

}
