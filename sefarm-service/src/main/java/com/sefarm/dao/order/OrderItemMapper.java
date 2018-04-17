package com.sefarm.dao.order;

import com.sefarm.common.base.SeFarmMapper;
import com.sefarm.common.vo.OrderItemVO;
import com.sefarm.model.order.OrderItemDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单项的数据访问层Mapper
 *
 * @author mc
 * @date 2018-3-24
 */
public interface OrderItemMapper extends SeFarmMapper<OrderItemDO> {

    List<OrderItemVO> getOrderItemVOList(@Param("name")String name, @Param("orderNo")String orderNo, @Param("productName")String productName, @Param("commentFlag")String commentFlag, @Param("createTimeBegin")String createTimeBegin, @Param("createTimeEnd")String createTimeEnd, @Param("sortName")String sort, @Param("sortOrder")String order);

    OrderItemVO getOrderItemVO(@Param("itemId") Long itemId);
}