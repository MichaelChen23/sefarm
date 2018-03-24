package com.sefarm.service.impl.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.system.SysResourceMapper;
import com.sefarm.model.system.SysResourceDO;
import com.sefarm.service.system.ISysResourceService;

/**
 * 系统资源的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class SysResourceServiceImpl extends BaseServiceImpl<SysResourceMapper, SysResourceDO> implements ISysResourceService {
}
