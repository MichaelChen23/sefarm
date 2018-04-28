package com.sefarm.service.impl.order;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.util.StrKit;
import com.sefarm.common.vo.ProductVO;
import com.sefarm.dao.order.OrderMapper;
import com.sefarm.model.order.OrderDO;
import com.sefarm.model.order.OrderItemDO;
import com.sefarm.service.impl.product.ProductServiceImpl;
import com.sefarm.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class OrderServiceImpl extends BaseServiceImpl<OrderMapper, OrderDO> implements IOrderService {

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    OrderItemServiceImpl orderItemService;

    @Override
    public PageInfo<OrderDO> getOrderDOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String orderNo, String status, String createTimeBegin, String createTimeEnd) {
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<OrderDO> list = getMapper().getOrderDOList(name, orderNo, status, createTimeBegin, createTimeEnd, StrKit.changeDBfieldPattern("", sortStr), orderStr);
        PageInfo<OrderDO> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public Integer saveOrderByObj(OrderDO orderDO) {
        return getMapper().saveOrderByObj(orderDO);
    }


    @Override
    public Long placeOrderByObj(OrderDO orderDO) {
        Map<Long, Integer> productMaps = new HashMap<>();
        productMaps.put(1L, 8);
        productMaps.put(2L, 8);
        productMaps.put(3L, 8);
        //先初始化订单，获取订单id
        Integer count = saveOrderByObj(orderDO);
        Long orderId = orderDO.getId();
        //该订单总产品数量
        Integer productAllNum = 0;
        //该订单总金额
        BigDecimal orderTotalAmount = new BigDecimal(0);
        //查询产品信息
        for (Map.Entry<Long, Integer> eachProduct : productMaps.entrySet()) {
            Long productId = eachProduct.getKey();
            Integer productNum = eachProduct.getValue();
            //根据产品id查询出产品信息
            ProductVO productInfo = productService.getProductVO(productId);
            //插入订单项
            OrderItemDO orderItemDO = new OrderItemDO();
            orderItemDO.setOrderId(orderId);
            orderItemDO.setProductId(productId);
            orderItemDO.setProductName(productInfo.getName());
            orderItemDO.setPrice(productInfo.getPrice());
            orderItemDO.setNumber(productNum);
            //累加产品总数
            productAllNum += productNum;
            orderItemDO.setUnit(productInfo.getUnit());
            BigDecimal num = new BigDecimal(productNum);
            BigDecimal total = productInfo.getPrice().multiply(num);
            orderItemDO.setTotal(total);
            //累加订单总金额
            orderTotalAmount = orderTotalAmount.add(total);
            orderItemDO.setAccount(orderDO.getAccount());
            orderItemDO.setCreateTime(orderDO.getCreateTime());
            orderItemService.saveByObj(orderItemDO);
        }
        //更新订单总数总额
        OrderDO order = new OrderDO();
        order.setId(orderId);
        //设置订单产品总数
        order.setQuantity(productAllNum);
        order.setProductTotal(orderTotalAmount);
        getMapper().updateByPrimaryKeySelective(order);
        return orderId;
    }
}
