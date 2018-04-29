package com.sefarm.service.order;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.common.vo.OrderItemVO;
import com.sefarm.model.order.OrderItemDO;

import java.util.List;

/**
 * 订单项的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface IOrderItemService extends IBaseService<OrderItemDO> {

    PageInfo<OrderItemVO> getOrderItemVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String orderNo, String productName, String commentFlag, String createTimeBegin, String createTimeEnd);

    OrderItemVO getOrderItemVO(Long itemId);

    /**
     * 移动前端——根据订单id查找所有订单项
     * add by mc 2018-4-29
     * @param orderId
     * @return
     */
    List<OrderItemDO> getOrderItemDOAllListByOrderId(Long orderId);
}
