package com.sefarm.controller.order;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.constant.state.OrderDeliveryStatus;
import com.sefarm.common.constant.tips.ErrorTip;
import com.sefarm.common.constant.tips.Tip;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.common.vo.OrderDeliveryVO;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.order.OrderDeliveryDO;
import com.sefarm.service.order.IOrderDeliveryService;
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
    public Tip save(@Valid OrderDeliveryDO orderDeliveryDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            Boolean res = orderDeliveryService.saveByObj(orderDeliveryDO);
            if (res) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("order-dely save fail(保存失败)--"+orderDeliveryDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
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
    public Tip update(@Valid OrderDeliveryDO orderDeliveryDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            if (orderDeliveryDO != null) {
                Boolean res = orderDeliveryService.updateByObj(orderDeliveryDO);
                if (res) {
                    return SUCCESS_TIP;
                }
            }
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        } catch (Exception e) {
            logger.error("order-dely update fail(更新失败)--"+orderDeliveryDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 删除 订单配送
     * @param deliveryId
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public Tip remove(@RequestParam Long deliveryId) {
        if (ToolUtil.isEmpty(deliveryId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            OrderDeliveryDO orderDeliveryDO = new OrderDeliveryDO();
            orderDeliveryDO.setId(deliveryId);
            Boolean result = orderDeliveryService.removeByObj(orderDeliveryDO);
            if (result) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("order-dely delete fail(删除失败)--"+deliveryId+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 订单配送——待发货
     * @param deliveryId
     * @return
     */
    @RequestMapping(value = "/ready", method = RequestMethod.POST)
    @ResponseBody
    public Tip readyOrder(@RequestParam Long deliveryId) {
        if (ToolUtil.isEmpty(deliveryId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            OrderDeliveryDO orderDeliveryDO = new OrderDeliveryDO();
            orderDeliveryDO.setId(deliveryId);
            orderDeliveryDO.setStatus(OrderDeliveryStatus.READY.getCode());
            orderDeliveryDO.setUpdateBy("sys");
            orderDeliveryDO.setUpdateTime(new Date());
            Boolean result = orderDeliveryService.updateByObj(orderDeliveryDO);
            if (result) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("order-dely ready fail(待发货失败)--"+deliveryId+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 订单配送——发货
     * @param deliveryId
     * @return
     */
    @RequestMapping(value = "/delivery", method = RequestMethod.POST)
    @ResponseBody
    public Tip deliveryOrder(@RequestParam Long deliveryId) {
        if (ToolUtil.isEmpty(deliveryId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            OrderDeliveryDO orderDeliveryDO = new OrderDeliveryDO();
            orderDeliveryDO.setId(deliveryId);
            orderDeliveryDO.setStatus(OrderDeliveryStatus.DELIVERY.getCode());
            orderDeliveryDO.setDeliveryTime(new Date());
            Boolean result = orderDeliveryService.updateByObj(orderDeliveryDO);
            if (result) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("order-dely delivery fail(发货失败)--"+deliveryId+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 订单配送——已签收
     * @param deliveryId
     * @return
     */
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    @ResponseBody
    public Tip receiveOrder(@RequestParam Long deliveryId) {
        if (ToolUtil.isEmpty(deliveryId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            OrderDeliveryDO orderDeliveryDO = new OrderDeliveryDO();
            orderDeliveryDO.setId(deliveryId);
            orderDeliveryDO.setStatus(OrderDeliveryStatus.RECEIVE.getCode());
            orderDeliveryDO.setReceiveTime(new Date());
            Boolean result = orderDeliveryService.updateByObj(orderDeliveryDO);
            if (result) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("order-dely receive fail(接收失败)--"+deliveryId+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }



    @RequestMapping(value = "/removeList", method = RequestMethod.POST)
    public BaseResponse<Boolean> removeList(@RequestBody String ids) {//批量删除
        try {
            List<String> list = JSON.parseArray(ids, String.class);
            Boolean result = orderDeliveryService.batchRemoveByIds(list);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("order-dely batch delete fail(批量删除失败)--"+ids+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public BaseResponse<OrderDeliveryDO> get(@RequestBody OrderDeliveryDO orderDeliveryDO) {//可以通过id来查找，也可以同唯一性的条件来查找出唯一的数据，例如username是不相同，唯一的，就可以定位到唯一的数据
        OrderDeliveryDO result = null;
        try {
            result = (OrderDeliveryDO) orderDeliveryService.getOneByObj(orderDeliveryDO);
            return new BaseResponse<OrderDeliveryDO>(result);
        } catch (Exception e) {
            logger.error("order-dely get fail(获取失败)--"+orderDeliveryDO.toString()+":{}", e.getMessage());
            return new BaseResponse(result);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageInfo<OrderDeliveryDO> getList(@RequestBody OrderDeliveryDO orderDeliveryDO) {//通过输入page页数和rows每页查询的行数来查询lsit，如果不输入，默认值查询第一页；如果改用select（Obj）方法输入唯一性字段来查询会查到相关唯一的记录。
        try {
            List<OrderDeliveryDO> list = orderDeliveryService.getListByObj(orderDeliveryDO);
            return new PageInfo<OrderDeliveryDO>(list);
        } catch (Exception e) {
            logger.error("order-dely get list fail(获取列表失败)--"+orderDeliveryDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public BaseResponse<List<OrderDeliveryDO>> getAll() {
        try {
            List<OrderDeliveryDO> list = orderDeliveryService.getALL();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("order-dely get all(获取所有数据失败)-- :{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Integer getCount(@RequestBody OrderDeliveryDO orderDeliveryDO) {//输入为null，查询全部的数量，输入唯一性的字段，根据该字段数值查询唯一，数量为1
        try {
            Integer count = orderDeliveryService.getCount(orderDeliveryDO);
            return count;
        } catch (Exception e) {
            logger.error("order-dely count fail(统计数目失败)--"+orderDeliveryDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    /**
     * 根据输入字段和值，进行模糊查询
     * @param orderDeliveryDO
     * @return
     * searchKey-查询的字段，searchValue-查询字段的值
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public PageInfo<OrderDeliveryDO> searchList(@RequestBody OrderDeliveryDO orderDeliveryDO) {
        try {
            List<OrderDeliveryDO> list = orderDeliveryService.searchListByKV(orderDeliveryDO);
            return new PageInfo<OrderDeliveryDO>(list);
        } catch (Exception e) {
            logger.error("order-dely search query fail(搜索查询失败)--"+orderDeliveryDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

}
