package com.sefarm.service.impl.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.node.MenuNode;
import com.sefarm.dao.system.SysMenuMapper;
import com.sefarm.model.system.SysMenuDO;
import com.sefarm.service.system.ISysMenuService;

import java.util.List;

/**
 * 系统菜单的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenuDO> implements ISysMenuService {

    @Override
    public List<MenuNode> getMenusByRoleId(Integer roleId) {
        return getMapper().getMenusByRoleId(roleId);
    }
}
