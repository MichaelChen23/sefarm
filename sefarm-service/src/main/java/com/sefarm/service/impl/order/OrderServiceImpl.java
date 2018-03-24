package com.sefarm.service.impl.order;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.order.OrderMapper;
import com.sefarm.model.order.OrderDO;
import com.sefarm.service.order.IOrderService;

/**
 * 订单的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class OrderServiceImpl extends BaseServiceImpl<OrderMapper, OrderDO> implements IOrderService {
}
