package com.sefarm.controller.order;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.constant.state.OrderDeliveryStatus;
import com.sefarm.common.constant.state.OrderStatus;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.common.vo.OrderDeliveryVO;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.order.OrderDO;
import com.sefarm.model.order.OrderDeliveryDO;
import com.sefarm.service.order.IOrderDeliveryService;
import com.sefarm.service.order.IOrderService;
import com.sefarm.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * 订单配送的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@Controller
@RequestMapping("/api/order-dely")
public class OrderDeliveryController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(OrderDeliveryController.class);

    private static String PREFIX = "/order/delivery/";

    @Autowired
    public IOrderDeliveryService orderDeliveryService;

    @Autowired
    public IOrderService orderService;

    /**
     * 跳转到查看 订单配送 列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "delivery.html";
    }

    /**
     * 跳转到新增 订单配送 的页面
     */
    @RequestMapping("/delivery_save")
    public String saveView() {
        return PREFIX + "delivery_save.html";
    }

    /**
     * 跳转到修改 订单配送 的页面
     */
    @RequestMapping("/delivery_update/{deliveryId}")
    public String updateView(@PathVariable Long deliveryId, Model model) {
        if(ToolUtil.isEmpty(deliveryId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        //获取订单配送的信息
        OrderDeliveryVO orderDeliveryVO = orderDeliveryService.getOrderDeliveryVO(deliveryId);
        model.addAttribute(orderDeliveryVO);
        return PREFIX + "delivery_update.html";
    }

    /**
     * 按照查询条件查询 订单配送 列表
     * @return
     */
    @RequestMapping(value = "/delivery_list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<OrderDeliveryVO> getOrderDeliveryVOList(@RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sortStr, @RequestParam(required = false) String orderStr, @RequestParam(required = false) String orderNo,
                                                            @RequestParam(required = false) String deptName, @RequestParam(required = false) String name, @RequestParam(required = false) String status, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd) {
        try {
            PageInfo<OrderDeliveryVO> result = orderDeliveryService.getOrderDeliveryVOList(pageIndex, pageSize, sortStr, orderStr, orderNo, deptName, name, status, createTimeBegin, createTimeEnd);
            return result;
        } catch (Exception e) {
            logger.error("get order-dely list fail(获取 订单配送 列表失败) -- :{}", e.getMessage());
            return null;
        }
    }

    /**
     * 添加 订单配送
     * @param orderDeliveryDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse save(@Valid OrderDeliveryDO orderDeliveryDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            Boolean res = orderDeliveryService.saveByObj(orderDeliveryDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "order-dely save fail(保存失败)--"+orderDeliveryDO.toString()+":{}", true);
        }
    }

    /**
     * 更新 订单配送
     * @param orderDeliveryDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@Valid OrderDeliveryDO orderDeliveryDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            Boolean res = orderDeliveryService.updateByObj(orderDeliveryDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "order-dely update fail(更新失败)--"+orderDeliveryDO.toString()+":{}", true);
        }
    }

    /**
     * 删除 订单配送
     * @param deliveryId
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse remove(@RequestParam Long deliveryId) {
        if (ToolUtil.isEmpty(deliveryId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            OrderDeliveryDO orderDeliveryDO = new OrderDeliveryDO();
            orderDeliveryDO.setId(deliveryId);
            Boolean res = orderDeliveryService.removeByObj(orderDeliveryDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "order-dely delete fail(删除失败)-- id:"+deliveryId+":{}", true);
        }
    }

    /**
     * 订单配送——待发货
     * @param deliveryId
     * @return
     */
    @RequestMapping(value = "/ready", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse readyOrder(@RequestParam Long deliveryId) {
        if (ToolUtil.isEmpty(deliveryId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            OrderDeliveryDO orderDeliveryDO = new OrderDeliveryDO();
            orderDeliveryDO.setId(deliveryId);
            orderDeliveryDO.setStatus(OrderDeliveryStatus.READY.getCode());
            orderDeliveryDO.setUpdateBy("sys");
            orderDeliveryDO.setUpdateTime(new Date());
            Boolean res = orderDeliveryService.updateByObj(orderDeliveryDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "order-dely ready fail(待发货失败)-- id:"+deliveryId+":{}", true);
        }
    }

    /**
     * 订单配送——发货
     * @param deliveryId
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/delivery", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse deliveryOrder(@RequestParam Long deliveryId, @RequestParam Long orderId) {
        if (ToolUtil.isEmpty(deliveryId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            OrderDeliveryDO orderDeliveryDO = new OrderDeliveryDO();
            orderDeliveryDO.setId(deliveryId);
            orderDeliveryDO.setStatus(OrderDeliveryStatus.DELIVERY.getCode());
            orderDeliveryDO.setDeliveryTime(new Date());
            Boolean res = orderDeliveryService.updateByObj(orderDeliveryDO);
            //更新订单状态为配送中
            OrderDO orderDO = new OrderDO();
            orderDO.setId(orderId);
            orderDO.setStatus(OrderStatus.SENDING.getCode());
            Boolean orderRes = orderService.updateByObj(orderDO);
            return BaseResponse.getRespByResultBool(res && orderRes);
        } catch (Exception e) {
            return handleException(e, "order-dely delivery fail(发货失败)-- id:"+deliveryId+":{}", true);
        }
    }

    /**
     * 订单配送——已签收
     * @param deliveryId
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse receiveOrder(@RequestParam Long deliveryId, @RequestParam Long orderId) {
        if (ToolUtil.isEmpty(deliveryId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            OrderDeliveryDO orderDeliveryDO = new OrderDeliveryDO();
            orderDeliveryDO.setId(deliveryId);
            orderDeliveryDO.setStatus(OrderDeliveryStatus.RECEIVE.getCode());
            orderDeliveryDO.setReceiveTime(new Date());
            Boolean res = orderDeliveryService.updateByObj(orderDeliveryDO);
            //更新订单状态为已完成
            OrderDO orderDO = new OrderDO();
            orderDO.setId(orderId);
            orderDO.setStatus(OrderStatus.DONE.getCode());
            Boolean orderRes = orderService.updateByObj(orderDO);
            return BaseResponse.getRespByResultBool(res && orderRes);
        } catch (Exception e) {
            return handleException(e, "order-dely receive fail(接收失败)-- id:"+deliveryId+":{}", true);
        }
    }

}
