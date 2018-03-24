package com.sefarm.service.impl.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.system.SysRoleMapper;
import com.sefarm.model.system.SysRoleDO;
import com.sefarm.service.system.ISysRoleService;

/**
 * 系统角色的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRoleDO> implements ISysRoleService {
}
