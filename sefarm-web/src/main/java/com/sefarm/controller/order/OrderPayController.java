package com.sefarm.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.constant.tips.ErrorTip;
import com.sefarm.common.constant.tips.Tip;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.common.vo.OrderPayVO;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.order.OrderPayDO;
import com.sefarm.service.order.IOrderPayService;
import com.sefarm.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 订单支付记录的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@Controller
@RequestMapping("/order-pay")
public class OrderPayController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(OrderPayController.class);

    private static String PREFIX = "/order/pay/";

    @Reference(version = "1.0.0", timeout = Constant.DUBBO_TIME_OUT)
    public IOrderPayService orderPayService;

    /**
     * 跳转到查看 订单支付记录 列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "pay.html";
    }

    /**
     * 跳转到新增 订单支付记录 的页面
     */
    @RequestMapping("/pay_save")
    public String saveView() {
        return PREFIX + "pay_save.html";
    }

    /**
     * 跳转到修改 订单支付记录 的页面
     */
    @RequestMapping("/pay_update/{payId}")
    public String updateView(@PathVariable Long payId, Model model) {
        if(ToolUtil.isEmpty(payId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        //获取订单支付记录的信息
        OrderPayVO orderPayVO = orderPayService.getOrderPayVO(payId);
        model.addAttribute(orderPayVO);
        return PREFIX + "pay_update.html";
    }

    /**
     * 按照查询条件查询 订单支付记录 列表
     * @return
     */
    @RequestMapping(value = "/pay_list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<OrderPayVO> getOrderPayVOList(@RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sortStr, @RequestParam(required = false) String orderStr, @RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String payAccount, @RequestParam(required = false) String orderNo, @RequestParam(required = false) String payStatus, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd) {
        try {
            PageInfo<OrderPayVO> result = orderPayService.getOrderPayVOList(pageIndex, pageSize, sortStr, orderStr, name, payAccount, orderNo, payStatus, createTimeBegin, createTimeEnd);
            return result;
        } catch (Exception e) {
            logger.error("get order-pay list fail(获取 订单支付记录 列表失败) -- :{}", e.getMessage());
            return null;
        }
    }

    /**
     * 添加 订单支付记录
     * @param orderPayDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Tip save(@Valid OrderPayDO orderPayDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            Boolean res = orderPayService.saveByObj(orderPayDO);
            if (res) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("order-pay save fail(保存失败)--"+orderPayDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 更新 订单支付记录
     * @param orderPayDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Tip update(@Valid OrderPayDO orderPayDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            if (orderPayDO != null) {
                Boolean res = orderPayService.updateByObj(orderPayDO);
                if (res) {
                    return SUCCESS_TIP;
                }
            }
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        } catch (Exception e) {
            logger.error("order-pay update fail(更新失败)--"+orderPayDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 删除 订单支付记录
     * @param payId
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public Tip remove(@RequestParam Long payId) {
        if (ToolUtil.isEmpty(payId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            OrderPayDO orderPayDO = new OrderPayDO();
            orderPayDO.setId(payId);
            Boolean result = orderPayService.removeByObj(orderPayDO);
            if (result) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("order-pay delete fail(删除失败)--"+payId+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    @RequestMapping(value = "/removeList", method = RequestMethod.POST)
    public BaseResponse<Boolean> removeList(@RequestBody String ids) {//批量删除
        try {
            List<String> list = JSON.parseArray(ids, String.class);
            Boolean result = orderPayService.batchRemoveByIds(list);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("order-pay batch delete fail(批量删除失败)--"+ids+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public BaseResponse<OrderPayDO> get(@RequestBody OrderPayDO orderPayDO) {//可以通过id来查找，也可以同唯一性的条件来查找出唯一的数据，例如username是不相同，唯一的，就可以定位到唯一的数据
        OrderPayDO result = null;
        try {
            result = (OrderPayDO) orderPayService.getOneByObj(orderPayDO);
            return new BaseResponse<OrderPayDO>(result);
        } catch (Exception e) {
            logger.error("order-pay get fail(获取失败)--"+orderPayDO.toString()+":{}", e.getMessage());
            return new BaseResponse(result);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageInfo<OrderPayDO> getList(@RequestBody OrderPayDO orderPayDO) {//通过输入page页数和rows每页查询的行数来查询lsit，如果不输入，默认值查询第一页；如果改用select（Obj）方法输入唯一性字段来查询会查到相关唯一的记录。
        try {
            List<OrderPayDO> list = orderPayService.getListByObj(orderPayDO);
            return new PageInfo<OrderPayDO>(list);
        } catch (Exception e) {
            logger.error("order-pay get list fail(获取列表失败)--"+orderPayDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public BaseResponse<List<OrderPayDO>> getAll() {
        try {
            List<OrderPayDO> list = orderPayService.getALL();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("order-pay get all(获取所有数据失败)-- :{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Integer getCount(@RequestBody OrderPayDO orderPayDO) {//输入为null，查询全部的数量，输入唯一性的字段，根据该字段数值查询唯一，数量为1
        try {
            Integer count = orderPayService.getCount(orderPayDO);
            return count;
        } catch (Exception e) {
            logger.error("order-pay count fail(统计数目失败)--"+orderPayDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    /**
     * 根据输入字段和值，进行模糊查询
     * @param orderPayDO
     * @return
     * searchKey-查询的字段，searchValue-查询字段的值
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public PageInfo<OrderPayDO> searchList(@RequestBody OrderPayDO orderPayDO) {
        try {
            List<OrderPayDO> list = orderPayService.searchListByKV(orderPayDO);
            return new PageInfo<OrderPayDO>(list);
        } catch (Exception e) {
            logger.error("order-pay search query fail(搜索查询失败)--"+orderPayDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

}
