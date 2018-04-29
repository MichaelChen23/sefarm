package com.sefarm.service.impl.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.util.StrKit;
import com.sefarm.common.vo.OrderItemVO;
import com.sefarm.dao.order.OrderItemMapper;
import com.sefarm.model.order.OrderItemDO;
import com.sefarm.service.order.IOrderItemService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 订单项的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service("orderItemService")
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItemMapper, OrderItemDO> implements IOrderItemService {

    @Override
    public PageInfo<OrderItemVO> getOrderItemVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String orderNo, String productName, String commentFlag, String createTimeBegin, String createTimeEnd) {
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<OrderItemVO> list = getMapper().getOrderItemVOList(name, orderNo, productName, commentFlag, createTimeBegin, createTimeEnd, StrKit.changeDBfieldPattern("", sortStr), orderStr);
        PageInfo<OrderItemVO> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public OrderItemVO getOrderItemVO(Long itemId) {
        return getMapper().getOrderItemVO(itemId);
    }

    @Override
    public List<OrderItemDO> getOrderItemDOAllListByOrderId(Long orderId) {
        Example example = new Example(OrderItemDO.class);
        example.createCriteria().andEqualTo("orderId", orderId);
        //把产品数量由大到小排列
        example.orderBy("number").desc();
        List<OrderItemDO> list = getMapper().selectByExample(example);
        return list;
    }
}
