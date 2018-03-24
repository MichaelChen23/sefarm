package com.sefarm.service.impl.order;

import com.alibaba.dubbo.config.annotation.Service;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.dao.order.OrderItemMapper;
import com.sefarm.model.order.OrderItemDO;
import com.sefarm.service.order.IOrderItemService;

/**
 * 订单项的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItemMapper, OrderItemDO> implements IOrderItemService {
}
