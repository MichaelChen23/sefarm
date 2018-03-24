package com.sefarm.service.impl.order;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.order.OrderPayMapper;
import com.sefarm.model.order.OrderPayDO;
import com.sefarm.service.order.IOrderPayService;

/**
 * 订单支付记录的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class OrderPayServiceImpl extends BaseServiceImpl<OrderPayMapper, OrderPayDO> implements IOrderPayService {
}
