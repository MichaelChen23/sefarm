package com.sefarm.controller.system;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.common.vo.SysUserVO;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.system.SysUserDO;
import com.sefarm.service.system.ISysUserService;
import com.sefarm.util.ShiroUtil;
import com.sefarm.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 系统用户的Controller
 *
 * @author mc
 * @date 2018-3-18
 *
 * controller对应beetl模版链接，restcontroller对应所有接口以restful方式返回结果，如果不用restController则要对每个接口使用@ResponseBody，两者只能选其一
 */
@Controller
@RequestMapping("/api/sys-user")
public class SysUserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    /**
     * 网页路径的前缀
     */
    private static String PREFIX = "/system/sysuser/";

    @Autowired
    public ISysUserService sysUserService;


    /**
     * 跳转到查看系统用户列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "sysuser.html";
    }

    /**
     * 跳转到新增系统用户的页面
     */
    @RequestMapping("/sysuser_save")
    public String saveView() {
        return PREFIX + "sysuser_save.html";
    }

    /**
     * 跳转到修改系统用户的页面
     */
    @RequestMapping("/sysuser_update/{userId}")
    public String updateView(@PathVariable Long userId, Model model) {
        if(ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        SysUserVO sysUserVO = sysUserService.getSysUserVO(userId);
        model.addAttribute(sysUserVO);
        return PREFIX + "sysuser_update.html";
    }

    /**
     * 跳转到分配系统角色的页面
     */
    @RequestMapping("/role_assign/{userId}")
    public String roleAssign(@PathVariable Long userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        SysUserDO sysUser = new SysUserDO();
        sysUser.setId(userId);
        SysUserDO sysUserDO = sysUserService.getOneByObj(sysUser);
        model.addAttribute("userId", userId);
        model.addAttribute("username", sysUserDO.getUsername());
        return PREFIX + "sysuser_roleassign.html";
    }

    /**
     * 按照查询条件查询系统用户列表
     * @return
     */
    @RequestMapping(value = "/sysuser_list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<SysUserVO> getSysUserList(@RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sortStr, @RequestParam(required = false) String orderStr,
                                              @RequestParam(required = false) Long sysDeptId, @RequestParam(required = false) String name, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd) {
        try {
            PageInfo<SysUserVO> result = sysUserService.getSysUserVOList(pageIndex, pageSize, sortStr, orderStr,sysDeptId, name, createTimeBegin, createTimeEnd);
            return result;
        } catch (Exception e) {
            logger.error("get sys-user list fail(获取系统用户列表失败) -- :{}", e.getMessage());
            return null;
        }
    }

    /**
     * 添加系统用户
     * @param sysUserDO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse save(@Valid SysUserDO sysUserDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        // 判断账号是否重复
        SysUserDO sysUserQuery = new SysUserDO();
        sysUserQuery.setUsername(sysUserDO.getUsername());
        SysUserDO theSysUser = sysUserService.getOneByObj(sysUserQuery);
        if (theSysUser != null) {
            throw new BussinessException(BizExceptionEnum.USER_ALREADY_REG);
        }

        try {
            // 完善账号信息
            sysUserDO.setCreateBy("sys");
            sysUserDO.setCreateTime(new Date());
            //使用Shiro工具加密用户密码
            sysUserDO.setSalt(ShiroUtil.getRandomSalt(5));
            sysUserDO.setPassword(ShiroUtil.md5(sysUserDO.getPassword(), sysUserDO.getSalt()));
            Boolean res = sysUserService.saveByObj(sysUserDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "sys-user save fail(保存失败)--"+sysUserDO.toString()+":{}", true);
        }
    }

    /**
     * 更新编辑 系统用户
     * @param sysUserDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@Valid SysUserDO sysUserDO, BindingResult result) {//一定要通过id来修改
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            sysUserDO.setUpdateBy("sys");
            sysUserDO.setUpdateTime(new Date());
            Boolean res = sysUserService.updateByObj(sysUserDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "sys-user update fail(更新失败)--"+sysUserDO.toString()+":{}", true);
        }
    }

    /**
     * 删除系统用户
     * @param userId
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse remove(@RequestParam Long userId) {//可通过id来删除，可通过其他条件是唯一性的来定位数据来删除，例如username是不相同，唯一的，就可以定位到唯一的数据
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            SysUserDO sysUserDO = new SysUserDO();
            sysUserDO.setId(userId);
            Boolean res = sysUserService.removeByObj(sysUserDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "sys-user delete fail(删除失败)-- id:"+userId+":{}", true);
        }
    }

    /**
     * 重置系统用户的密码 设置为默认密码888888
     * @param userId
     * @return
     */
    @RequestMapping("/reset")
    @ResponseBody
    public BaseResponse resetPassword(@RequestParam Long userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            SysUserDO sysUser = new SysUserDO();
            sysUser.setId(userId);
            SysUserDO sysUserDO = sysUserService.getOneByObj(sysUser);
            sysUserDO.setPassword(Constant.DEFAULT_SYS_USER_PWD);
            sysUserDO.setUpdateBy("sys");
            sysUserDO.setUpdateTime(new Date());
            Boolean res = sysUserService.updateByObj(sysUserDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "sys-user reset password fail(重置密码失败)-- id:"+userId+":{}", true);
        }
    }

    /**
     * 分配系统角色
     * @param userId
     * @param roleIds
     * @return
     */
    @RequestMapping("/setSysRole")
    @ResponseBody
    public BaseResponse setSysRole(@RequestParam("userId") Long userId, @RequestParam("roleIds") String roleIds) {
        if (ToolUtil.isOneEmpty(userId, roleIds)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            String[] roleIdArray = roleIds.split(",");
            SysUserDO sysUser = new SysUserDO();
            sysUser.setId(userId);
            SysUserDO sysUserDO = sysUserService.getOneByObj(sysUser);
            //只允许选择--最后选中的角色
            sysUserDO.setSysRoleId(Long.valueOf(roleIdArray[0]));
            sysUserDO.setUpdateBy("sys");
            sysUserDO.setUpdateTime(new Date());
            Boolean res = sysUserService.updateByObj(sysUserDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "sys-user set sys-role fail(设置系统角色失败)-- userId:"+userId+"-- roleIds:"+roleIds+":{}", true);
        }
    }

    /**
     * 根据部门id查询部门下所有系统用户
     * @param deptId
     * @return
     */
    @RequestMapping(value = "/list_by_deptid", method = RequestMethod.POST)
    @ResponseBody
    public List<SysUserDO> getListByDeptId(@RequestParam Long deptId) {
        try {
            //必须有选择dept的情况下才查询部门下的系统用户
            if (!ToolUtil.isEmpty(deptId)) {
                SysUserDO sysUserDO = new SysUserDO();
                sysUserDO.setSysDeptId(deptId);
                List<SysUserDO> list = sysUserService.getAllByObj(sysUserDO);
                return list;
            }
            return null;
        } catch (Exception e) {
            logger.error("sys-user get list by deptId fail(获取列表失败)--"+deptId+":{}", e.getMessage());
            return null;
        }
    }

}
