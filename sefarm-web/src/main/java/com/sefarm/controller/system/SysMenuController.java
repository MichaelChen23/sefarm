package com.sefarm.controller.system;

import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.common.node.ZTreeNode;
import com.sefarm.common.vo.SysMenuTreeVO;
import com.sefarm.common.vo.SysMenuVO;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.system.SysMenuDO;
import com.sefarm.service.system.ISysMenuService;
import com.sefarm.util.ToolUtil;
import org.apache.commons.lang3.StringUtils;
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
 * 系统菜单的Controller
 *
 * @author mc
 * @date 2018-4-12
 */
@Controller
@RequestMapping("/api/sys-menu")
public class SysMenuController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysMenuController.class);

    private static String PREFIX = "/system/sysmenu/";

    @Autowired
    public ISysMenuService sysMenuService;

    /**
     * 跳转到系统菜单列表页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "sysmenu.html";
    }

    /**
     * 跳转到新增系统菜单的页面
     */
    @RequestMapping("/sysmenu_save")
    public String saveView() {
        return PREFIX + "sysmenu_save.html";
    }

    /**
     * 跳转到修改系统菜单的页面
     */
    @RequestMapping("/sysmenu_update/{menuId}")
    public String updateView(@PathVariable Long menuId, Model model) {
        if(ToolUtil.isEmpty(menuId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        SysMenuVO sysMenuVO = sysMenuService.getSysMenuVO(menuId);
        model.addAttribute(sysMenuVO);
        return PREFIX + "sysmenu_update.html";
    }

    /**
     * 角色权限配置，根据角色id获取权限菜单树
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/getMenuTreeByRoleId/{roleId}")
    @ResponseBody
    public List<ZTreeNode> getMenuTreeByRoleId(@PathVariable Long roleId) {
        List<Long> menuIds = sysMenuService.getMenuIdsByRoleId(roleId);
        if (ToolUtil.isEmpty(menuIds)) {
            List<ZTreeNode> allMenuTree = sysMenuService.getAllMenuTree();
            return allMenuTree;
        } else {
            List<ZTreeNode> menuTreeByRoleId = sysMenuService.getMemuTreeByMenuIds(menuIds);
            return menuTreeByRoleId;
        }
    }

    /**
     * 获取所有的菜单，在新增，编辑弹窗里的选择上级菜单
     * @return
     */
    @RequestMapping(value = "/getAllMenuTree")
    @ResponseBody
    public List<ZTreeNode> getAllMenuTree() {
        List<ZTreeNode> list = sysMenuService.getAllMenuTree();
        list.add(ZTreeNode.createParent());
        return list;
    }

    /**
     * 获取所有的菜单，在菜单列表
     * @return
     */
    @RequestMapping(value = "/all_list", method = RequestMethod.POST)
    @ResponseBody
    public List<SysMenuTreeVO> getAll(@RequestParam(required = false) String name, @RequestParam(required = false) Integer level, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd) {
        try {
            List<SysMenuTreeVO> list = sysMenuService.getSysMenuDOAllList(name, level, createTimeBegin, createTimeEnd);
            return list;
        } catch (Exception e) {
            logger.error("sys-menu get all(获取所有数据失败)-- :{}", e.getMessage());
            return null;
        }
    }

    /**
     * 添加系统菜单
     * @param sysMenuDO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse save(@Valid SysMenuDO sysMenuDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            // 完善菜单信息
            sysMenuDO.setCreateBy("sys");
            sysMenuDO.setCreateTime(new Date());
            //设上级菜单和层级
            setMenuPcodeByPMenuId(sysMenuDO, true);

            Boolean res = sysMenuService.saveByObj(sysMenuDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "sys-menu save fail(保存失败)--"+sysMenuDO.toString()+":{}", true);
        }
    }

    /**
     * 更新编辑 系统菜单
     * @param sysMenuDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@Valid SysMenuDO sysMenuDO, BindingResult result) {//一定要通过id来修改
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            sysMenuDO.setUpdateBy("sys");
            sysMenuDO.setUpdateTime(new Date());
            //设上级菜单和层级
            setMenuPcodeByPMenuId(sysMenuDO, false);
            Boolean res = sysMenuService.updateByObj(sysMenuDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "sys-menu update fail(更新失败)--"+sysMenuDO.toString()+":{}", true);
        }
    }

    /**
     * 删除系统菜单
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse remove(@RequestParam Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            Boolean res = sysMenuService.removeAllSubMenusByMenuId(menuId);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "sys-menu delete fail(删除失败)-- id:"+menuId+":{}", true);
        }
    }

    /**
     * 根据上级菜单id设置pcode和层级
     * @param isByPid 是否通过pid来查找上级
     */
    private void setMenuPcodeByPMenuId(@Valid SysMenuDO sysMenuDO, Boolean isByPid) {
        if (StringUtils.isBlank(sysMenuDO.getPcode()) || sysMenuDO.getPcode().equals("0")) {
            sysMenuDO.setPcode("0");
            sysMenuDO.setPcodes("[0],");
            sysMenuDO.setLevels(1);
        } else {
            //新增保存的时候，前端把上级菜单id存进了pcode
            SysMenuDO menuDO = new SysMenuDO();
            if (isByPid) {
                Long PMenuId = Long.parseLong(sysMenuDO.getPcode());
                menuDO.setId(PMenuId);
            } else {
                menuDO.setCode(sysMenuDO.getPcode());
            }
            SysMenuDO pMenu = sysMenuService.getOneByObj(menuDO);

            Integer pLevels = pMenu.getLevels();
            sysMenuDO.setPcode(pMenu.getCode());

            //如果编号和父编号一致会导致无限递归
            if (sysMenuDO.getCode().equals(sysMenuDO.getPcode())) {
                throw new BussinessException(BizExceptionEnum.MENU_PCODE_COINCIDENCE);
            }

            sysMenuDO.setLevels(pLevels + 1);
            sysMenuDO.setPcodes(pMenu.getPcodes() + "[" + pMenu.getCode() + "],");
        }
    }
}
