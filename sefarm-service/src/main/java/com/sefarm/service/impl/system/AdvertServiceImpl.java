package com.sefarm.service.impl.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.system.AdvertMapper;
import com.sefarm.model.system.AdvertDO;
import com.sefarm.service.system.IAdvertService;

/**
 * 广告的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class AdvertServiceImpl extends BaseServiceImpl<AdvertMapper, AdvertDO> implements IAdvertService {
}
