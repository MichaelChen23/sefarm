package com.sefarm.service.impl.order;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.util.StrKit;
import com.sefarm.dao.order.OrderMapper;
import com.sefarm.model.order.OrderDO;
import com.sefarm.service.order.IOrderService;

import java.util.List;

/**
 * 订单的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class OrderServiceImpl extends BaseServiceImpl<OrderMapper, OrderDO> implements IOrderService {

    @Override
    public PageInfo<OrderDO> getOrderDOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String orderNo, String status, String createTimeBegin, String createTimeEnd) {
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<OrderDO> list = getMapper().getOrderDOList(name, orderNo, status, createTimeBegin, createTimeEnd, StrKit.changeDBfieldPattern("", sortStr), orderStr);
        PageInfo<OrderDO> page = new PageInfo<>(list);
        return page;
    }
}
