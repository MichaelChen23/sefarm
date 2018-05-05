package com.sefarm.service.impl.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.util.StrKit;
import com.sefarm.common.vo.CartVO;
import com.sefarm.dao.common.CartMapper;
import com.sefarm.dao.order.OrderItemMapper;
import com.sefarm.dao.order.OrderMapper;
import com.sefarm.model.order.OrderDO;
import com.sefarm.model.order.OrderItemDO;
import com.sefarm.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service("orderService")
public class OrderServiceImpl extends BaseServiceImpl<OrderMapper, OrderDO> implements IOrderService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Override
    public PageInfo<OrderDO> getOrderDOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String orderNo, String status, String createTimeBegin, String createTimeEnd) {
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<OrderDO> list = getMapper().getOrderDOList(name, orderNo, status, createTimeBegin, createTimeEnd, StrKit.changeDBfieldPattern("", sortStr), orderStr);
        PageInfo<OrderDO> page = new PageInfo<>(list);
        return page;
    }

    /**
     * 移动前端——下订单，配置事务
     * add by mc 2018-4-29
     * @param orderDO
     * @param cartIdArray
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = Constant.DEFAULT_TRANSACTION_TIMEOUT, rollbackFor = Exception.class)
    @Override
    public Long placeOrderByObj(OrderDO orderDO, Long[] cartIdArray) {
        //先初始化订单，获取订单id
        Integer count = getMapper().saveOrderByObj(orderDO);
        Long orderId = orderDO.getId();
        //该订单总产品数量
        Integer productAllNum = 0;
        //该订单总金额
        BigDecimal orderTotalAmount = new BigDecimal(0);
        //查询 购物车 产品信息
        for (int i = 0 ; i < cartIdArray.length ; i++) {
            Long cartId = cartIdArray[i];
            //根据购物车id查询出产品消息
            CartVO cartVO = cartMapper.getCartVO(cartId);
            //插入订单项
            OrderItemDO orderItemDO = new OrderItemDO();
            orderItemDO.setOrderId(orderId);
            orderItemDO.setProductId(cartVO.getProductId());
            orderItemDO.setProductName(cartVO.getProductName());
            orderItemDO.setPrice(cartVO.getNowPrice());
            orderItemDO.setNumber(cartVO.getNumber());
            //累加产品总数
            productAllNum += cartVO.getNumber();
            orderItemDO.setUnit(cartVO.getUnit());
            BigDecimal num = new BigDecimal(cartVO.getNumber());
            BigDecimal total = cartVO.getNowPrice().multiply(num);
            orderItemDO.setTotal(total);
            //累加订单总金额
            orderTotalAmount = orderTotalAmount.add(total);
            orderItemDO.setAccount(orderDO.getAccount());
            orderItemDO.setCreateTime(orderDO.getCreateTime());
            orderItemMapper.insertSelective(orderItemDO);
            //删除已经下单成功的购物车产品
            cartMapper.deleteByPrimaryKey(cartId);
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

    @Override
    public PageInfo<OrderDO> getOrderDOPageList(Integer pageIndex, Integer pageSize, String account) {
        Example example = new Example(OrderDO.class);
        example.createCriteria().andEqualTo("account", account);
        example.orderBy("createTime").desc();
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<OrderDO> list = getMapper().selectByExample(example);
        PageInfo<OrderDO> page = new PageInfo<>(list);
        return page;
    }
}
