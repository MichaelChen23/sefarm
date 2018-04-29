package com.sefarm.controller.order;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.constant.tips.ErrorTip;
import com.sefarm.common.constant.tips.Tip;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.common.util.NumberUtil;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.order.OrderDO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@Controller
@RequestMapping("/api/order")
public class OrderController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private static String PREFIX = "/order/base/";

    @Autowired
    IOrderService orderService;

    /**
     * 跳转到查看 订单 列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "order.html";
    }

    /**
     * 跳转到新增 订单 的页面
     */
    @RequestMapping("/order_save")
    public String saveView() {
        return PREFIX + "order_save.html";
    }

    /**
     * 跳转到修改 订单 的页面
     */
    @RequestMapping("/order_update/{orderId}")
    public String updateView(@PathVariable Long orderId, Model model) {
        if(ToolUtil.isEmpty(orderId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        OrderDO query = new OrderDO();
        query.setId(orderId);
        OrderDO orderDO = orderService.getOneByObj(query);
        model.addAttribute(orderDO);
        return PREFIX + "order_update.html";
    }

    /**
     * 按照查询条件查询 订单列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<OrderDO> getOrderDOList(@RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sortStr, @RequestParam(required = false) String orderStr, @RequestParam(required = false) String name,
                                            @RequestParam(required = false) String orderNo, @RequestParam(required = false) String status, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd) {
        try {
            PageInfo<OrderDO> result = orderService.getOrderDOList(pageIndex, pageSize, sortStr, orderStr, name, orderNo, status, createTimeBegin, createTimeEnd);
            return result;
        } catch (Exception e) {
            logger.error("get order list fail(获取 订单列表失败) -- :{}", e.getMessage());
            return null;
        }
    }

    /**
     * 新增 订单
     * @param orderDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Tip save(@Valid OrderDO orderDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            Boolean res = orderService.saveByObj(orderDO);
            if (res) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("order save fail(保存失败)--"+orderDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 更新编辑 订单
     * @param orderDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Tip update(@Valid OrderDO orderDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            if (orderDO != null) {
                orderDO.setUpdateBy("sys");
                orderDO.setUpdateTime(new Date());
                Boolean res = orderService.updateByObj(orderDO);
                if (res) {
                    return SUCCESS_TIP;
                }
            }
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        } catch (Exception e) {
            logger.error("order update fail(更新失败)--"+orderDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 删除 订单
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public Tip remove(@RequestParam Long orderId) {
        if (ToolUtil.isEmpty(orderId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            OrderDO orderDO = new OrderDO();
            orderDO.setId(orderId);
            Boolean result = orderService.removeByObj(orderDO);
            if (result) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("order delete fail(删除失败)--"+orderId+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 移动前端——下订单
     * add by mc 2018-4-29
     * @param account
     * @param name
     * @param mobile
     * @param address
     * @param productJsonStr
     * @param requirement
     * @return 返回orderNo订单编号
     */
    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<String> placeOrder(@RequestParam String account, @RequestParam String name, @RequestParam String mobile, @RequestParam String address, @RequestParam String productJsonStr, @RequestParam(required = false) String requirement) {
        Map<String, Integer> productMaps = new HashMap<>();
        try {
            //解析所选产品jsonStr为map
            productMaps = (Map<String, Integer>) JSON.parse(productJsonStr);
            OrderDO orderDO = new OrderDO();
            //获取唯一的订单号，线程安全
            String orderNo = NumberUtil.getUniqueOrderNo();
            orderDO.setOrderNo(orderNo);
            orderDO.setAccount(account);
            orderDO.setRequirement(requirement);
            orderDO.setCreateTime(new Date());
            //下订单 返回订单id
            Long id = orderService.placeOrderByObj(orderDO, productMaps);
            if (id > 0) {
                //返回订单号给前端，去支付
                return new BaseResponse(orderNo);
            } else {
                return new BaseResponse(null);
            }
        } catch (Exception e) {
            logger.error("place order fail(下订单失败)--"+account+"--"+productMaps.toString()+":{}", e.getMessage());
            return new BaseResponse(null);
        }
    }

    /**
     * 移动前端——分页查询订单
     * @param pageIndex
     * @param pageSize
     * @param account
     * @return
     */
    @RequestMapping(value = "/getPageList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<PageInfo<OrderDO>> getOrderPageList(@RequestParam Integer pageIndex, @RequestParam Integer pageSize, @RequestParam String account) {
        try {
            PageInfo<OrderDO> result = orderService.getOrderDOPageList(pageIndex, pageSize, account);
            return new BaseResponse<>(result);
        } catch (Exception e) {
            logger.error("get order page list fail(按条件查询 订单 列表失败) -- :{}", e.getMessage());
            return new BaseResponse<>(null);
        }
    }



    @RequestMapping(value = "/removeList", method = RequestMethod.POST)
    public BaseResponse<Boolean> removeList(@RequestBody String ids) {//批量删除
        try {
            List<String> list = JSON.parseArray(ids, String.class);
            Boolean result = orderService.batchRemoveByIds(list);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("order batch delete fail(批量删除失败)--"+ids+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public BaseResponse<OrderDO> get(@RequestBody OrderDO orderDO) {//可以通过id来查找，也可以同唯一性的条件来查找出唯一的数据，例如username是不相同，唯一的，就可以定位到唯一的数据
        OrderDO result = null;
        try {
            result = (OrderDO) orderService.getOneByObj(orderDO);
            return new BaseResponse<OrderDO>(result);
        } catch (Exception e) {
            logger.error("order get fail(获取失败)--"+orderDO.toString()+":{}", e.getMessage());
            return new BaseResponse(result);
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public BaseResponse<List<OrderDO>> getAll() {
        try {
            List<OrderDO> list = orderService.getALL();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("order get all(获取所有数据失败)-- :{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Integer getCount(@RequestBody OrderDO orderDO) {//输入为null，查询全部的数量，输入唯一性的字段，根据该字段数值查询唯一，数量为1
        try {
            Integer count = orderService.getCount(orderDO);
            return count;
        } catch (Exception e) {
            logger.error("order count fail(统计数目失败)--"+orderDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    /**
     * 根据输入字段和值，进行模糊查询
     * @param orderDO
     * @return
     * searchKey-查询的字段，searchValue-查询字段的值
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public PageInfo<OrderDO> searchList(@RequestBody OrderDO orderDO) {
        try {
            List<OrderDO> list = orderService.searchListByKV(orderDO);
            return new PageInfo<OrderDO>(list);
        } catch (Exception e) {
            logger.error("order search query fail(搜索查询失败)--"+orderDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

}
