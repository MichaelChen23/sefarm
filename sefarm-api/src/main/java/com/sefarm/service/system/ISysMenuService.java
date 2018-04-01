package com.sefarm.service.system;

import com.sefarm.common.base.IBaseService;
import com.sefarm.common.node.MenuNode;
import com.sefarm.model.system.SysMenuDO;

import java.util.List;

/**
 * 系统菜单的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface ISysMenuService extends IBaseService<SysMenuDO> {

    /**
     * 根据角色获取菜单 用于首页
     * 一用户一角色
     * @param roleId
     * @return
     */
    List<MenuNode> getMenusByRoleId(Integer roleId);
}
