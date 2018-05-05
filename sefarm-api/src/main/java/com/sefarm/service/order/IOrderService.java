package com.sefarm.service.order;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.model.order.OrderDO;

import java.util.Map;

/**
 * 订单的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface IOrderService extends IBaseService<OrderDO> {

    /**
     * 按条件分页查询 订单列表
     * @param pageIndex
     * @param pageSize
     * @param sortStr
     * @param orderStr
     * @param name
     * @param orderNo
     * @param status
     * @param createTimeBegin
     * @param createTimeEnd
     * @return
     */
    PageInfo<OrderDO> getOrderDOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String orderNo, String status, String createTimeBegin, String createTimeEnd);

    /**
     * 移动前端——下订单
     * add by mc 2018-4-29
     * @param orderDO
     * @param cartIdArray
     * @return
     */
    Long placeOrderByObj(OrderDO orderDO, Long[] cartIdArray);

    /**
     * 移动前端——查询订单列表
     * @param pageIndex
     * @param pageSize
     * @param account
     * @return
     */
    PageInfo<OrderDO> getOrderDOPageList(Integer pageIndex, Integer pageSize, String account);
}
