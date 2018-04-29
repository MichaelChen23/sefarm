package com.sefarm.service.impl.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.util.StrKit;
import com.sefarm.common.vo.OrderDeliveryVO;
import com.sefarm.dao.order.OrderDeliveryMapper;
import com.sefarm.model.order.OrderDeliveryDO;
import com.sefarm.service.order.IOrderDeliveryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单配送的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service("orderDeliveryService")
public class OrderDeliveryServiceImpl extends BaseServiceImpl<OrderDeliveryMapper, OrderDeliveryDO> implements IOrderDeliveryService {

    @Override
    public PageInfo<OrderDeliveryVO> getOrderDeliveryVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String orderNo, String deptName, String name, String status, String createTimeBegin, String createTimeEnd) {
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<OrderDeliveryVO> list = getMapper().getOrderDeliveryVOList(orderNo, deptName, name, status, createTimeBegin, createTimeEnd, StrKit.changeDBfieldPattern("", sortStr), orderStr);
        PageInfo<OrderDeliveryVO> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public OrderDeliveryVO getOrderDeliveryVO(Long deliveryId) {
        return getMapper().getOrderDeliveryVO(deliveryId);
    }
}
