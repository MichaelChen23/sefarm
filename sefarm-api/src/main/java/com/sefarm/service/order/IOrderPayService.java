package com.sefarm.service.order;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.IBaseService;
import com.sefarm.common.vo.OrderPayVO;
import com.sefarm.model.order.OrderPayDO;

/**
 * 订单支付记录的服务接口
 *
 * @author mc
 * @date 2018-3-24
 */
public interface IOrderPayService extends IBaseService<OrderPayDO> {

    PageInfo<OrderPayVO> getOrderPayVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String payAccount, String orderNo, String payStatus, String createTimeBegin, String createTimeEnd);

    OrderPayVO getOrderPayVO(Long payId);
}
