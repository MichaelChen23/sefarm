package com.sefarm.service.impl.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.system.SysRoleMenuMapper;
import com.sefarm.model.system.SysRoleMenuDO;
import com.sefarm.service.system.ISysRoleMenuService;

/**
 * 系统角色菜单关联的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuMapper, SysRoleMenuDO> implements ISysRoleMenuService {
}
