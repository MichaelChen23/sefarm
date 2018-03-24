package com.sefarm.service.impl.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.system.SysUserMapper;
import com.sefarm.model.system.SysUserDO;
import com.sefarm.service.system.ISysUserService;

/**
 * 系统用户的服务接口实现
 *
 * @author mc
 * @date 2018-3-18
 */
@Service(version = "1.0.0")
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUserDO> implements ISysUserService {

}
