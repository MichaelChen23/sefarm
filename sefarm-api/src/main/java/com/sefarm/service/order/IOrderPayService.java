package com.sefarm.service.order;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.common.vo.OrderPayVO;
import com.sefarm.model.order.OrderPayDO;

import java.util.List;

/**
 * 订单支付记录的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface IOrderPayService extends IBaseService<OrderPayDO> {

    PageInfo<OrderPayVO> getOrderPayVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String orderNo, String payStatus, String createTimeBegin, String createTimeEnd);

    OrderPayVO getOrderPayVO(Long payId);

    /**
     * 根据订单id查找所有的支付记录
     * add by mc 2018-5-21
     * @param orderId
     * @return
     */
    List<OrderPayVO> getOrderPayVOByOrderId(Long orderId);

    /**
     * 根据订单号查找所有的支付记录
     * add by mc 2018-6-12
     * @param orderNo
     * @return
     */
    List<OrderPayVO> getOrderPayVOByOrderNo(String orderNo);
}
