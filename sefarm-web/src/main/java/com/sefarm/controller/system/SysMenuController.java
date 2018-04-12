package com.sefarm.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sefarm.common.node.ZTreeNode;
import com.sefarm.controller.common.BaseController;
import com.sefarm.service.system.ISysMenuService;
import com.sefarm.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统菜单的Controller
 *
 * @author mc
 * @date 2018-4-12
 */
@Controller
@RequestMapping("/sys-menu")
public class SysMenuController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysMenuController.class);

    private static String PREFIX = "/system/sysmenu/";

    @Reference(version = "1.0.0", timeout = 10000)
    public ISysMenuService sysMenuService;

    /**
     * 跳转到系统菜单列表页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "sysmenu.html";
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

}
