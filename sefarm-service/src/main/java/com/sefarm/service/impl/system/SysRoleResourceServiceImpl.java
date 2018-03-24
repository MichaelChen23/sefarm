package com.sefarm.service.impl.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.system.SysRoleResourceMapper;
import com.sefarm.model.system.SysRoleResourceDO;
import com.sefarm.service.system.ISysRoleResourceService;

/**
 * 系统角色资源的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class SysRoleResourceServiceImpl extends BaseServiceImpl<SysRoleResourceMapper, SysRoleResourceDO> implements ISysRoleResourceService {
}
