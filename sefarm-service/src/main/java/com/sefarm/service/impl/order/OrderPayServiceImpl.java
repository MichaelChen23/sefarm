package com.sefarm.service.impl.order;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.util.StrKit;
import com.sefarm.common.vo.OrderPayVO;
import com.sefarm.dao.order.OrderPayMapper;
import com.sefarm.model.order.OrderPayDO;
import com.sefarm.service.order.IOrderPayService;

import java.util.List;

/**
 * 订单支付记录的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service(version = "1.0.0")
public class OrderPayServiceImpl extends BaseServiceImpl<OrderPayMapper, OrderPayDO> implements IOrderPayService {

    @Override
    public PageInfo<OrderPayVO> getOrderPayVOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String payAccount, String orderNo, String payStatus, String createTimeBegin, String createTimeEnd) {
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<OrderPayVO> list = getMapper().getOrderPayVOList(name, payAccount, orderNo, payStatus, createTimeBegin, createTimeEnd, StrKit.changeDBfieldPattern("", sortStr), orderStr);
        PageInfo<OrderPayVO> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public OrderPayVO getOrderPayVO(Long payId) {
        return getMapper().getOrderPayVO(payId);
    }
}
