package com.sefarm.service.impl.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.user.UserRankMapper;
import com.sefarm.model.user.UserRankDO;
import com.sefarm.service.user.IUserRankService;

/**
 * 用户等级的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class UserRankServiceImpl extends BaseServiceImpl<UserRankMapper, UserRankDO> implements IUserRankService {
}
