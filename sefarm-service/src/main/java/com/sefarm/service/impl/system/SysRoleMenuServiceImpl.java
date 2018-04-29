package com.sefarm.service.impl.system;

import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.system.SysRoleMenuMapper;
import com.sefarm.model.system.SysRoleMenuDO;
import com.sefarm.service.system.ISysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 系统角色菜单关联的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuMapper, SysRoleMenuDO> implements ISysRoleMenuService {
}
