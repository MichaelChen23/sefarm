package com.sefarm.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.constant.tips.ErrorTip;
import com.sefarm.common.constant.tips.Tip;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.common.node.ZTreeNode;
import com.sefarm.common.vo.SysRoleVO;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.system.SysRoleDO;
import com.sefarm.service.system.ISysRoleService;
import com.sefarm.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 系统角色的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@Controller
@RequestMapping("/api/sys-role")
public class SysRoleController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    private static String PREFIX = "/system/sysrole/";

    @Reference(version = "1.0.0", timeout = Constant.DUBBO_TIME_OUT)
    public ISysRoleService sysRoleService;


    /**
     * 跳转到系统角色列表页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "sysrole.html";
    }

    /**
     * 跳转到新增系统角色的页面
     */
    @RequestMapping("/sysrole_save")
    public String saveView() {
        return PREFIX + "sysrole_save.html";
    }

    /**
     * 跳转到修改系统角色的页面
     */
    @RequestMapping("/sysrole_update/{roleId}")
    public String updateView(@PathVariable Long roleId, Model model) {
        if(ToolUtil.isEmpty(roleId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        SysRoleVO sysRoleVO = sysRoleService.getSysRoleVO(roleId);
        model.addAttribute(sysRoleVO);
        return PREFIX + "sysrole_update.html";
    }

    /**
     * 跳转到系统角色权限配置的页面
     */
    @RequestMapping("/sysrole_assignauth/{roleId}")
    public String roleAssignAuthView(@PathVariable Long roleId, Model model) {
        if(ToolUtil.isEmpty(roleId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        SysRoleVO sysRoleVO = sysRoleService.getSysRoleVO(roleId);
        model.addAttribute(sysRoleVO);
        return PREFIX + "sysrole_assignauth.html";
    }

    /**
     * 获取系统角色树
     * @return
     */
    @RequestMapping(value = "/sysRoleTree")
    @ResponseBody
    public List<ZTreeNode> getSysRoleTree() {
        List<ZTreeNode> sysRoleTree = sysRoleService.getSysRoleTree();
        sysRoleTree.add(ZTreeNode.createParent());
        return sysRoleTree;
    }

    /**
     * 按照查询条件查询系统角色列表
     * @return
     */
    @RequestMapping(value = "/sysrole_list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<SysRoleVO> getSysRoleList(@RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sortStr, @RequestParam(required = false) String orderStr,
                                              @RequestParam(required = false) String name, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd) {
        try {
            PageInfo<SysRoleVO> result = sysRoleService.getSysRoleVOList(pageIndex, pageSize, sortStr, orderStr, name, createTimeBegin, createTimeEnd);
            return result;
        } catch (Exception e) {
            logger.error("get sys-role list fail(获取系统角色列表失败) -- :{}", e.getMessage());
            return null;
        }
    }


    /**
     * 添加系统角色
     * @param sysRoleDO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Tip save(@Valid SysRoleDO sysRoleDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        // 判断角色代码是否重复
        SysRoleDO sysRoleQuery = new SysRoleDO();
        sysRoleQuery.setCode(sysRoleDO.getCode());
        SysRoleDO theSysRole = sysRoleService.getOneByObj(sysRoleQuery);
        if (theSysRole != null) {
            throw new BussinessException(BizExceptionEnum.USER_ALREADY_REG);
        }

        try {
            // 完善角色信息
            sysRoleDO.setCreateBy("sys");
            sysRoleDO.setCreateTime(new Date());

            Boolean res = sysRoleService.saveByObj(sysRoleDO);
            if (res) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("sys-role save fail(保存失败)--"+sysRoleDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 更新编辑 系统角色
     * @param sysRoleDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Tip update(@Valid SysRoleDO sysRoleDO, BindingResult result) {//一定要通过id来修改
        try {
            if (result.hasErrors()) {
                throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
            }
            if (sysRoleDO != null) {
                sysRoleDO.setUpdateBy("sys");
                sysRoleDO.setUpdateTime(new Date());
                Boolean res = sysRoleService.updateByObj(sysRoleDO);
                if (res) {
                    return SUCCESS_TIP;
                }
            }
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        } catch (Exception e) {
            logger.error("sys-role update fail(更新失败)--"+sysRoleDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 删除系统角色
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public Tip remove(@RequestParam Long roleId) {//可通过id来删除，可通过其他条件是唯一性的来定位数据来删除，例如username是不相同，唯一的，就可以定位到唯一的数据
        try {
            if (ToolUtil.isEmpty(roleId)) {
                throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
            }
            SysRoleDO sysRoleDO = new SysRoleDO();
            sysRoleDO.setId(roleId);
            Boolean result = sysRoleService.removeByObj(sysRoleDO);
            if (result) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("sys-role delete fail(删除失败)--"+roleId+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 配置系统角色的菜单权限
     * @param roleId
     * @param ids
     * @return
     */
    @RequestMapping("/set_authority")
    @ResponseBody
    public Tip setMenuAuthority(@RequestParam("roleId") Long roleId, @RequestParam("ids") String ids) {
        try {
            if (ToolUtil.isOneEmpty(roleId)) {
                throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
            }
            sysRoleService.setMenuAuthority(roleId, ids);
            return SUCCESS_TIP;
        } catch (Exception e) {
            logger.error("sys-role set menu authority fail(系统角色配置菜单权限失败)--"+roleId+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }




    @RequestMapping(value = "/removeList", method = RequestMethod.POST)
    public BaseResponse<Boolean> removeList(@RequestBody String ids) {//批量删除
        try {
            List<String> list = JSON.parseArray(ids, String.class);
            Boolean result = sysRoleService.batchRemoveByIds(list);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("sys-role batch delete fail(批量删除失败)--"+ids+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public BaseResponse<SysRoleDO> get(@RequestBody SysRoleDO sysRoleDO) {//可以通过id来查找，也可以同唯一性的条件来查找出唯一的数据，例如username是不相同，唯一的，就可以定位到唯一的数据
        SysRoleDO result = null;
        try {
            result = (SysRoleDO) sysRoleService.getOneByObj(sysRoleDO);
            return new BaseResponse<SysRoleDO>(result);
        } catch (Exception e) {
            logger.error("sys-role get fail(获取失败)--"+sysRoleDO.toString()+":{}", e.getMessage());
            return new BaseResponse(result);
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public BaseResponse<List<SysRoleDO>> getAll() {
        try {
            List<SysRoleDO> list = sysRoleService.getALL();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("sys-role get all(获取所有数据失败)-- :{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Integer getCount(@RequestBody SysRoleDO sysRoleDO) {//输入为null，查询全部的数量，输入唯一性的字段，根据该字段数值查询唯一，数量为1
        try {
            Integer count = sysRoleService.getCount(sysRoleDO);
            return count;
        } catch (Exception e) {
            logger.error("sys-role count fail(统计数目失败)--"+sysRoleDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    /**
     * 根据输入字段和值，进行模糊查询
     * @param sysRoleDO
     * @return
     * searchKey-查询的字段，searchValue-查询字段的值
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public PageInfo<SysRoleDO> searchList(@RequestBody SysRoleDO sysRoleDO) {
        try {
            List<SysRoleDO> list = sysRoleService.searchListByKV(sysRoleDO);
            return new PageInfo<SysRoleDO>(list);
        } catch (Exception e) {
            logger.error("sys-role search query fail(搜索查询失败)--"+sysRoleDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

}
