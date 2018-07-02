package com.sefarm.controller.system;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.common.node.ZTreeNode;
import com.sefarm.common.vo.SysDeptVO;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.system.SysDeptDO;
import com.sefarm.service.system.ISysDeptService;
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
 * 系统部门组织的Controller
 *
 * @author mc
 * @date 2018-4-4
 */
@Controller
@RequestMapping("/api/sys-dept")
public class SysDeptController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysDeptController.class);

    /**
     * 网页路径的前缀
     */
    private static String PREFIX = "/system/sysdept/";

    @Autowired
    public ISysDeptService sysDeptService;

    /**
     * 跳转到查看系统部门列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "sysdept.html";
    }

    /**
     * 跳转到新增系统部门的页面
     */
    @RequestMapping("/sysdept_save")
    public String saveView() {
        return PREFIX + "sysdept_save.html";
    }

    /**
     * 跳转到修改系统部门的页面
     */
    @RequestMapping("/sysdept_update/{deptId}")
    public String updateView(@PathVariable Long deptId, Model model) {
        if(ToolUtil.isEmpty(deptId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        SysDeptVO sysDeptVO = sysDeptService.getSysDeptVO(deptId);
        model.addAttribute(sysDeptVO);
        return PREFIX + "sysdept_update.html";
    }

    /**
     * 获取部门的tree列表
     */
    @RequestMapping(value = "/getDeptTree")
    @ResponseBody
    public List<ZTreeNode> getDeptTree() {
        List<ZTreeNode> tree = sysDeptService.getDeptTree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    /**
     * 按照查询条件查询系统部门列表
     * @return
     */
    @RequestMapping(value = "/sysdept_list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<SysDeptVO> getSysDeptList(@RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sortStr, @RequestParam(required = false) String orderStr,
                                              @RequestParam(required = false) String name, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd) {
        try {
            PageInfo<SysDeptVO> result = sysDeptService.getSysDeptVOList(pageIndex, pageSize, sortStr, orderStr, name, createTimeBegin, createTimeEnd);
            return result;
        } catch (Exception e) {
            logger.error("get sys-dept list fail(获取系统部门列表失败) -- :{}", e.getMessage());
            return null;
        }
    }

    /**
     * 添加系统部门
     * @param sysDeptDO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse save(@Valid SysDeptDO sysDeptDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        // 判断部门是否重复
        SysDeptDO sysDeptQuery = new SysDeptDO();
        sysDeptQuery.setName(sysDeptDO.getName());
        SysDeptDO theSysDept = sysDeptService.getOneByObj(sysDeptQuery);
        if (theSysDept != null) {
            throw new BussinessException(BizExceptionEnum.USER_ALREADY_REG);
        }

        try {
            // 完善部门信息，设置当前操作用户为创建人
            sysDeptDO.setCreateBy(getCurrentSysUser());
            sysDeptDO.setCreateTime(new Date());
            Boolean res = sysDeptService.saveByObj(sysDeptDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "sys-dept save fail(保存失败)--"+sysDeptDO.toString()+":{}", true);
        }
    }

    /**
     * 更新编辑 系统部门
     * @param sysDeptDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@Valid SysDeptDO sysDeptDO, BindingResult result) {//一定要通过id来修改
        try {
            if (result.hasErrors()) {
                throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
            }
            //设置当前操作用户为更新人
            sysDeptDO.setUpdateBy(getCurrentSysUser());
            sysDeptDO.setUpdateTime(new Date());
            Boolean res = sysDeptService.updateByObj(sysDeptDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "sys-dept update fail(更新失败)--"+sysDeptDO.toString()+":{}", true);
        }
    }

    /**
     * 删除系统部门
     * @param deptId
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse remove(@RequestParam Long deptId) {
        try {
            if (ToolUtil.isEmpty(deptId)) {
                throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
            }
            SysDeptDO sysDeptDO = new SysDeptDO();
            sysDeptDO.setId(deptId);
            Boolean res = sysDeptService.removeByObj(sysDeptDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "sys-dept delete fail(删除失败)-- id:"+deptId+":{}", true);
        }
    }


}
