package com.sefarm.dao.system;

import com.sefarm.common.base.SeFarmMapper;
import com.sefarm.common.node.MenuNode;
import com.sefarm.common.node.ZTreeNode;
import com.sefarm.common.vo.SysMenuTreeVO;
import com.sefarm.common.vo.SysMenuVO;
import com.sefarm.model.system.SysMenuDO;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 按条件获取所有的菜单列表
     * @param name
     * @param level
     * @param createTimeBegin
     * @param createTimeEnd
     * @return
     */
    List<SysMenuTreeVO> getSysMenuDOAllList(@Param("name")String name, @Param("level")Integer level, @Param("createTimeBegin")String createTimeBegin, @Param("createTimeEnd")String createTimeEnd);

    /**
     * 编辑菜单信息--根据菜单id获取菜单信息
     * @param sysMenuId
     * @return
     */
    SysMenuVO getSysMenuVO(@Param("sysMenuId")Long sysMenuId);
}