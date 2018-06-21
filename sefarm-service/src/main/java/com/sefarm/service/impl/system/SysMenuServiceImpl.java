package com.sefarm.service.impl.system;

import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.node.MenuNode;
import com.sefarm.common.node.ZTreeNode;
import com.sefarm.common.vo.SysMenuTreeVO;
import com.sefarm.common.vo.SysMenuVO;
import com.sefarm.dao.system.SysMenuMapper;
import com.sefarm.model.system.SysMenuDO;
import com.sefarm.service.system.ISysMenuService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 系统菜单的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenuDO> implements ISysMenuService {

    @Override
    public List<MenuNode> getMenusByRoleId(Long roleId) {
        return getMapper().getMenusByRoleId(roleId);
    }

    @Override
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        return getMapper().getMenuIdsByRoleId(roleId);
    }

    @Override
    public List<String> getMenuUrlsByRoleId(Long roleId) {
        return getMapper().getMenuUrlsByRoleId(roleId);
    }

    @Override
    public List<ZTreeNode> getAllMenuTree() {
        return getMapper().getAllMenuTree();
    }

    @Override
    public List<ZTreeNode> getMemuTreeByMenuIds(List<Long> menuIds) {
        return getMapper().getMemuTreeByMenuIds(menuIds);
    }

    @Override
    public List<SysMenuTreeVO> getSysMenuDOAllList(String name, Integer level, String createTimeBegin, String createTimeEnd) {
        return getMapper().getSysMenuDOAllList(name, level, createTimeBegin, createTimeEnd);
    }

    @Override
    public SysMenuVO getSysMenuVO(Long sysMenuId) {
        return getMapper().getSysMenuVO(sysMenuId);
    }

    @Override
    public Boolean removeAllSubMenusByMenuId(Long MenuId) {
        SysMenuDO pSysMenuDO = getMapper().selectByPrimaryKey(MenuId);
        //删除当前菜单
        removeByObj(pSysMenuDO);
        //删除所有子菜单
        Example example = new Example(SysMenuDO.class);
        example.createCriteria().andLike("pcodes","%[" + pSysMenuDO.getCode() + "]%");
        Integer res = getMapper().deleteByExample(example);
        return res > 0;
    }

}
