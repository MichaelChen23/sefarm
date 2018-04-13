package com.sefarm.service.system;

import com.sefarm.common.base.IBaseService;
import com.sefarm.common.node.MenuNode;
import com.sefarm.common.node.ZTreeNode;
import com.sefarm.common.vo.SysMenuTreeVO;
import com.sefarm.common.vo.SysMenuVO;
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

    /**
     * 按条件查询所有的菜单列表
     * @param name
     * @param level
     * @param createTimeBegin
     * @param createTimeEnd
     * @return
     */
    List<SysMenuTreeVO> getSysMenuDOAllList(String name, Integer level, String createTimeBegin, String createTimeEnd);

    /**
     * 编辑菜单信息--根据菜单id获取菜单信息
     * @param sysMenuId
     * @return
     */
    SysMenuVO getSysMenuVO(Long sysMenuId);

    /**
     * 根据菜单id删除其下所有的子菜单
     * @param MenuId
     */
    void removeAllSubMenusByMenuId(Long MenuId);
}
