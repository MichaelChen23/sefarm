package com.sefarm.dao.system;

import com.sefarm.common.base.SeFarmMapper;
import com.sefarm.common.node.MenuNode;
import com.sefarm.common.node.ZTreeNode;
import com.sefarm.model.system.SysMenuDO;

import java.util.List;

/**
 * 系统菜单的数据访问层Mapper
 *
 * @author mc
 * @date 2018-3-24
 */
public interface SysMenuMapper extends SeFarmMapper<SysMenuDO> {

    /**
     * 根据用户角色来获取所能操作菜单，一用户一角色
     * @param roleId
     * @return
     */
    List<MenuNode> getMenusByRoleId(Long roleId);

    /**
     * 根据角色id获取其权限下的菜单id
     * @param roleId
     * @return
     */
    List<Long> getMenuIdsByRoleId(Long roleId);

    /**
     * 获取所有的菜单树
     * @return
     */
    List<ZTreeNode> getAllMenuTree();

    /**
     * 根据用户权限下的菜单id获取菜单树
     * @param menuIds
     * @return
     */
    List<ZTreeNode> getMemuTreeByMenuIds(List<Long> menuIds);
}