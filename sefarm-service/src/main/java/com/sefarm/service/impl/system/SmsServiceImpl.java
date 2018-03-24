package com.sefarm.service.impl.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.system.SmsMapper;
import com.sefarm.model.system.SmsDO;
import com.sefarm.service.system.ISmsService;

/**
 * 短信的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class SmsServiceImpl extends BaseServiceImpl<SmsMapper, SmsDO> implements ISmsService {
}
