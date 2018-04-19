package com.sefarm.service.order;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.common.vo.OrderDeliveryVO;
import com.sefarm.model.order.OrderDeliveryDO;

/**
 * 订单配送的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface IOrderDeliveryService extends IBaseService<OrderDeliveryDO> {

    PageInfo<OrderDeliveryVO> getOrderDeliveryVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String orderNo, String deptName, String name, String status, String createTimeBegin, String createTimeEnd);

    OrderDeliveryVO getOrderDeliveryVO(Long deliveryId);
}
