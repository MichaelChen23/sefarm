package com.sefarm.controller.order;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.common.vo.OrderItemVO;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.order.OrderItemDO;
import com.sefarm.service.order.IOrderItemService;
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
import java.util.List;

/**
 * 订单项的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@Controller
@RequestMapping("/api/order-item")
public class OrderItemController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(OrderItemController.class);

    private static String PREFIX = "/order/item/";

    @Autowired
    public IOrderItemService orderItemService;

    /**
     * 跳转到查看 订单项 列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "item.html";
    }

    /**
     * 跳转到新增 订单项 的页面
     */
    @RequestMapping("/item_save")
    public String saveView() {
        return PREFIX + "item_save.html";
    }

    /**
     * 跳转到修改 订单项 的页面
     */
    @RequestMapping("/item_update/{itemId}")
    public String updateView(@PathVariable Long itemId, Model model) {
        if(ToolUtil.isEmpty(itemId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        //获取产品的信息
        OrderItemVO orderItemVO = orderItemService.getOrderItemVO(itemId);
        model.addAttribute(orderItemVO);
        return PREFIX + "item_update.html";
    }

    /**
     * 按照查询条件查询 订单项 列表
     * @return
     */
    @RequestMapping(value = "/item_list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<OrderItemVO> getOrderItemVOList(@RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sortStr, @RequestParam(required = false) String orderStr, @RequestParam(required = false) String name,
                                                    @RequestParam(required = false) String orderNo, @RequestParam(required = false) String productName, @RequestParam(required = false) String commentFlag, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd) {
        try {
            PageInfo<OrderItemVO> result = orderItemService.getOrderItemVOList(pageIndex, pageSize, sortStr, orderStr, name, orderNo, productName, commentFlag, createTimeBegin, createTimeEnd);
            return result;
        } catch (Exception e) {
            logger.error("get order item list fail(获取 订单项 列表失败) -- :{}", e.getMessage());
            return null;
        }
    }

    /**
     * 添加 订单项
     * @param orderItemDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse save(@Valid OrderItemDO orderItemDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            Boolean res = orderItemService.saveByObj(orderItemDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return baseException.handleException(e, logger, "order-item save fail(保存失败)--"+orderItemDO.toString()+":{}", true);
        }
    }

    /**
     * 更新编辑 订单项
     * @param orderItemDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@Valid OrderItemDO orderItemDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            orderItemDO.setUpdateBy("sys");
            orderItemDO.setUpdateTime(new Date());
            Boolean res = orderItemService.updateByObj(orderItemDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return baseException.handleException(e, logger, "order-item update fail(更新失败)--"+orderItemDO.toString()+":{}", true);
        }
    }

    /**
     * 删除 订单项
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse remove(@RequestParam Long itemId) {
        if (ToolUtil.isEmpty(itemId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            OrderItemDO orderItemDO = new OrderItemDO();
            orderItemDO.setId(itemId);
            Boolean res = orderItemService.removeByObj(orderItemDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return baseException.handleException(e, logger, "order-item delete fail(删除失败)-- id:"+itemId+":{}", true);
        }
    }

    /**
     * 移动前端——根据订单id查找所有订单项
     * add by mc 2018-4-29
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/getAllListByOrderId", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<OrderItemDO>> getOrderItemAllListByOrderId(@RequestParam Long orderId) {
        try {
            List<OrderItemDO> list = orderItemService.getOrderItemDOAllListByOrderId(orderId);
            return new BaseResponse<>(list);
        } catch (Exception e) {
            return baseException.handleException(e, logger, "order-item get all list by orderId (获取全部订单项list失败)-- id:" + orderId + ":{}", false);
        }
    }

}
