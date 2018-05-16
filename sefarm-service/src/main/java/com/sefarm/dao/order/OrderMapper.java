package com.sefarm.dao.order;

import com.sefarm.common.base.SeFarmMapper;
import com.sefarm.model.order.OrderDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单的数据访问层Mapper
 *
 * @author mc
 * @date 2018-3-24
 */
public interface OrderMapper extends SeFarmMapper<OrderDO> {

    /**
     * 按条件查询 订单列表
     * @param name
     * @param orderNo
     * @param status
     * @param createTimeBegin
     * @param createTimeEnd
     * @param sort
     * @param order
     * @return
     */
    List<OrderDO> getOrderDOList(@Param("name")String name, @Param("orderNo")String orderNo, @Param("status")String status, @Param("createTimeBegin")String createTimeBegin, @Param("createTimeEnd")String createTimeEnd, @Param("sortName")String sort, @Param("sortOrder")String order);

    /**
     * 保存订单信息，并且返回id
     * @param orderDO
     * @return
     */
    Integer saveOrderByObj(OrderDO orderDO);
}