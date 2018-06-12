package com.sefarm.dao.order;

import com.sefarm.common.base.SeFarmMapper;
import com.sefarm.common.vo.OrderPayVO;
import com.sefarm.model.order.OrderPayDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单支付记录的数据访问层Mapper
 *
 * @author mc
 * @date 2018-3-24
 */
public interface OrderPayMapper extends SeFarmMapper<OrderPayDO> {

    List<OrderPayVO> getOrderPayVOList(@Param("name")String name, @Param("orderNo")String orderNo, @Param("payStatus")String payStatus, @Param("createTimeBegin")String createTimeBegin, @Param("createTimeEnd")String createTimeEnd, @Param("sortName")String sort, @Param("sortOrder")String order);

    OrderPayVO getOrderPayVO(@Param("payId") Long payId);

    List<OrderPayVO> getOrderPayVOByOrderId(@Param("orderId") Long orderId);

    List<OrderPayVO> getOrderPayVOByOrderNo(@Param("orderNo") String orderNo);

}