package com.sefarm.dao.order;

import com.sefarm.common.base.SeFarmMapper;
import com.sefarm.common.vo.OrderDeliveryVO;
import com.sefarm.model.order.OrderDeliveryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单配送的数据访问层Mapper
 *
 * @author mc
 * @date 2018-4-19
 */
public interface OrderDeliveryMapper extends SeFarmMapper<OrderDeliveryDO> {

    List<OrderDeliveryVO> getOrderDeliveryVOList(@Param("orderNo")String orderNo, @Param("deptName")String deptName, @Param("name")String name, @Param("status")String status, @Param("createTimeBegin")String createTimeBegin, @Param("createTimeEnd")String createTimeEnd, @Param("sortName")String sort, @Param("sortOrder")String order);

    OrderDeliveryVO getOrderDeliveryVO(@Param("deliveryId") Long deliveryId);

}