package com.sefarm.service.impl.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.util.StrKit;
import com.sefarm.common.vo.CartVO;
import com.sefarm.common.vo.OrderDetailVO;
import com.sefarm.common.vo.OrderItemDetailVO;
import com.sefarm.dao.common.CartMapper;
import com.sefarm.dao.order.OrderDeliveryMapper;
import com.sefarm.dao.order.OrderItemMapper;
import com.sefarm.dao.order.OrderMapper;
import com.sefarm.model.order.OrderDO;
import com.sefarm.model.order.OrderDeliveryDO;
import com.sefarm.model.order.OrderItemDO;
import com.sefarm.model.user.UserAddressDO;
import com.sefarm.service.order.IOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Autowired
    OrderDeliveryMapper orderDeliveryMapper;

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
     * @param userAddressDO
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = Constant.DEFAULT_TRANSACTION_TIMEOUT, rollbackFor = Exception.class)
    @Override
    public Long placeOrderByObj(OrderDO orderDO, Long[] cartIdArray, UserAddressDO userAddressDO) {
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
            orderItemDO.setProductImage(cartVO.getProductImage());
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
        //设置总费用=产品总额+运费总额
        order.setAmount(orderTotalAmount.add(order.getCarryFeeTotal() != null ? order.getCarryFeeTotal() :  new BigDecimal(0)));
        //之前插入数据的时候，要返回id，所以没有把status设置为默认值，现在设置status为未完成
        order.setStatus(Constant.STATUS_LOCK);
        getMapper().updateByPrimaryKeySelective(order);

        //把用户地址设置在订单配送里
        OrderDeliveryDO orderDeliveryDO = new OrderDeliveryDO();
        orderDeliveryDO.setOrderId(orderId);
        orderDeliveryDO.setReceiver(userAddressDO.getName());
        orderDeliveryDO.setAddress(userAddressDO.getAddress());
        if (StringUtils.isNotBlank(userAddressDO.getMobile())) {
            orderDeliveryDO.setMobile(userAddressDO.getMobile());
        } else {
            orderDeliveryDO.setMobile(userAddressDO.getPhone());
        }
        //把客户需求存放在配送备注上
        orderDeliveryDO.setRemark(orderDO.getRequirement());
        orderDeliveryDO.setCreateTime(orderDO.getCreateTime());
        orderDeliveryMapper.insertSelective(orderDeliveryDO);
        return orderId;
    }

    @Override
    public OrderDetailVO getOrderDetailByOrderId(Long orderId) {
        OrderDO orderDO = getMapper().selectByPrimaryKey(orderId);
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        //获取订单基本信息
        orderDetailVO.setId(orderDO.getId());
        orderDetailVO.setOrderNo(orderDO.getOrderNo());
        orderDetailVO.setAccount(orderDO.getAccount());
        orderDetailVO.setPayType(orderDO.getPayType());
        orderDetailVO.setCarry(orderDO.getCarry());
        orderDetailVO.setCarryFeeTotal(orderDO.getCarryFeeTotal());
        orderDetailVO.setQuantity(orderDO.getQuantity());
        orderDetailVO.setProductTotal(orderDO.getProductTotal());
        orderDetailVO.setAmount(orderDO.getAmount());
        orderDetailVO.setExchangeScore(orderDO.getExchangeScore());
        orderDetailVO.setRequirement(orderDO.getRequirement());
        orderDetailVO.setStatus(orderDO.getStatus());
        orderDetailVO.setCreateTime(orderDO.getCreateTime());
        //获取配送信息
        OrderDeliveryDO delyQuery = new OrderDeliveryDO();
        delyQuery.setOrderId(orderId);
        OrderDeliveryDO orderDeliveryDO = orderDeliveryMapper.selectOne(delyQuery);
        if (orderDeliveryDO != null) {
            orderDetailVO.setReceiver(orderDeliveryDO.getReceiver());
            orderDetailVO.setAddress(orderDeliveryDO.getAddress());
            orderDetailVO.setMobile(orderDeliveryDO.getMobile());
            orderDetailVO.setDeliveryStatus(orderDeliveryDO.getStatus());
            orderDetailVO.setDeliveryTime(orderDeliveryDO.getDeliveryTime());
            orderDetailVO.setReceiveTime(orderDeliveryDO.getReceiveTime());
        }
        //获取所有订单项信息
        List<OrderItemDetailVO> orderItemDetailVOList = new ArrayList<>();
        Example example = new Example(OrderItemDO.class);
        example.createCriteria().andEqualTo("orderId", orderId);
        //把产品数量由大到小排列
        example.orderBy("number").desc();
        List<OrderItemDO> orderItemDOList = orderItemMapper.selectByExample(example);
        if (orderItemDOList != null && orderItemDOList.size() > 0) {
            for (OrderItemDO orderItemDO : orderItemDOList) {
                OrderItemDetailVO orderItemDetailVO = new OrderItemDetailVO();
                orderItemDetailVO.setId(orderItemDO.getId());
                orderItemDetailVO.setProductId(orderItemDO.getProductId());
                orderItemDetailVO.setProductName(orderItemDO.getProductName());
                orderItemDetailVO.setProductImage(orderItemDO.getProductImage());
                orderItemDetailVO.setPrice(orderItemDO.getPrice());
                orderItemDetailVO.setNumber(orderItemDO.getNumber());
                orderItemDetailVO.setUnit(orderItemDO.getUnit());
                orderItemDetailVO.setCarryFee(orderItemDO.getCarryFee());
                orderItemDetailVO.setTotal(orderItemDO.getTotal());
                orderItemDetailVO.setCommentFlag(orderItemDO.getCommentFlag());
                orderItemDetailVOList.add(orderItemDetailVO);
            }
            orderDetailVO.setOrderItemDetailVOList(orderItemDetailVOList);
        }
        return orderDetailVO;
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
