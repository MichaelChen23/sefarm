package com.sefarm.service.impl.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.user.UserAddressMapper;
import com.sefarm.model.user.UserAddressDO;
import com.sefarm.service.user.IUserAddressService;

/**
 * 用户地址的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class UserAddressServiceImpl extends BaseServiceImpl<UserAddressMapper, UserAddressDO> implements IUserAddressService {
}
