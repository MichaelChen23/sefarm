package com.sefarm.service.impl.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.system.SysLogMapper;
import com.sefarm.model.system.SysLogDO;
import com.sefarm.service.system.ISysLogService;

/**
 * 系统日志的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLogDO> implements ISysLogService {
}
