package com.sefarm.service.impl.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.system.SysDictMapper;
import com.sefarm.model.system.SysDictDO;
import com.sefarm.service.system.ISysDictService;

/**
 * 系统字典的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class SysDictServiceImpl extends BaseServiceImpl<SysDictMapper, SysDictDO> implements ISysDictService {
}
