package com.sefarm.dao.system;

import com.sefarm.common.base.SeFarmMapper;
import com.sefarm.common.node.MenuNode;
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
    List<MenuNode> getMenusByRoleId(Integer roleId);
}