package com.sefarm.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.node.ZTreeNode;
import com.sefarm.common.util.StrKit;
import com.sefarm.common.vo.SysRoleVO;
import com.sefarm.dao.system.SysRoleMapper;
import com.sefarm.dao.system.SysRoleMenuMapper;
import com.sefarm.model.system.SysRoleDO;
import com.sefarm.model.system.SysRoleMenuDO;
import com.sefarm.service.system.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统角色的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRoleDO> implements ISysRoleService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<ZTreeNode> getSysRoleTree() {
        return getMapper().getSysRoleTree();
    }

    @Override
    public PageInfo<SysRoleVO> getSysRoleVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String createTimeBegin, String createTimeEnd) {
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<SysRoleVO> list = getMapper().getSysRoleVOList(name, createTimeBegin, createTimeEnd, StrKit.changeDBfieldPattern("", sortStr), orderStr);
        PageInfo<SysRoleVO> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public SysRoleVO getSysRoleVO(Long sysRoleId) {
        return getMapper().getSysRoleVO(sysRoleId);
    }

    @Override
    public void setMenuAuthority(Long roleId, String ids) {
        Example example = new Example(SysRoleMenuDO.class);
        example.createCriteria().andEqualTo("sysRoleId", roleId);
        // 先查出当前角色是否有权限
        List<SysRoleMenuDO> sysRoleMenuDOList = sysRoleMenuMapper.selectByExample(example);
        // 删除该角色所有的权限
        sysRoleMenuMapper.deleteByExample(example);

        //批量插入新的权限
        List<SysRoleMenuDO> sysRoleMenuList = new ArrayList<>();
        Long[] menuIdArray = StrKit.idsStrToLongArray(ids);
        if (menuIdArray.length > 0) {
            for (int i = 0; i < menuIdArray.length; i++) {
                if (menuIdArray[i] != null && menuIdArray[i] > 0) {
                    SysRoleMenuDO sysRoleMenuDO = new SysRoleMenuDO();
                    sysRoleMenuDO.setSysRoleId(roleId);
                    sysRoleMenuDO.setSysMenuId(menuIdArray[i]);
                    if (sysRoleMenuDOList != null && sysRoleMenuDOList.size() > 0) {
                        sysRoleMenuDO.setCreateBy(sysRoleMenuDOList.get(0).getCreateBy());
                        sysRoleMenuDO.setCreateTime(sysRoleMenuDOList.get(0).getCreateTime());
                        sysRoleMenuDO.setUpdateBy("sys");
                        sysRoleMenuDO.setUpdateTime(new Date());
                    } else {
                        sysRoleMenuDO.setCreateBy("sys");
                        sysRoleMenuDO.setCreateTime(new Date());
                    }
                    sysRoleMenuList.add(sysRoleMenuDO);
                }
            }
            sysRoleMenuMapper.insertList(sysRoleMenuList);
        }
    }

}
