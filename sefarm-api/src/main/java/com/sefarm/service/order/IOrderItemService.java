package com.sefarm.service.order;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.common.vo.OrderItemVO;
import com.sefarm.model.order.OrderItemDO;

/**
 * 订单项的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface IOrderItemService extends IBaseService<OrderItemDO> {

    PageInfo<OrderItemVO> getOrderItemVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String orderNo, String productName, String commentFlag, String createTimeBegin, String createTimeEnd);

    OrderItemVO getOrderItemVO(Long itemId);
}
