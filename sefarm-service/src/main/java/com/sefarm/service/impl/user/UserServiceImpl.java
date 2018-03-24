package com.sefarm.service.impl.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.user.UserMapper;
import com.sefarm.model.user.UserDO;
import com.sefarm.service.user.IUserService;

/**
 * 用户的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserDO> implements IUserService {
}
