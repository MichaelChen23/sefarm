package com.sefarm.service.impl.order;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.order.OrderDeliveryMapper;
import com.sefarm.model.order.OrderDeliveryDO;
import com.sefarm.service.order.IOrderDeliveryService;

/**
 * 订单配送的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class OrderDeliveryServiceImpl extends BaseServiceImpl<OrderDeliveryMapper, OrderDeliveryDO> implements IOrderDeliveryService {
}
