package com.sefarm.controller.order;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.constant.state.OrderStatus;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.common.util.DateUtil;
import com.sefarm.common.util.HttpKit;
import com.sefarm.common.util.NumberUtil;
import com.sefarm.common.util.StrKit;
import com.sefarm.common.vo.OrderDetailVO;
import com.sefarm.common.vo.OrderPayVO;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.order.OrderDO;
import com.sefarm.model.order.OrderItemDO;
import com.sefarm.model.order.OrderPayDO;
import com.sefarm.model.user.UserAddressDO;
import com.sefarm.model.user.UserDO;
import com.sefarm.service.order.IOrderItemService;
import com.sefarm.service.order.IOrderPayService;
import com.sefarm.service.order.IOrderService;
import com.sefarm.service.user.IUserAddressService;
import com.sefarm.service.user.IUserService;
import com.sefarm.util.ToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@Controller
@RequestMapping("/api")
public class OrderController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private static String PREFIX = "/order/base/";

    @Autowired
    IOrderService orderService;

    @Autowired
    IUserService userService;

    @Autowired
    IUserAddressService userAddressService;

    @Autowired
    IOrderPayService orderPayService;

    @Autowired
    IOrderItemService orderItemService;

    /**
     * 跳转到查看 订单 列表的页面
     */
    @RequestMapping("/order")
    public String index() {
        return PREFIX + "order.html";
    }

    /**
     * 跳转到新增 订单 的页面
     */
    @RequestMapping("/order/order_save")
    public String saveView() {
        return PREFIX + "order_save.html";
    }

    /**
     * 跳转到修改 订单 的页面
     */
    @RequestMapping("/order/order_update/{orderId}")
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
    @RequestMapping(value = "/order/list", method = RequestMethod.POST)
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
    @RequestMapping(value = "/order/save", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse save(@Valid OrderDO orderDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            Boolean res = orderService.saveByObj(orderDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "order save fail(保存失败)--"+orderDO.toString()+":{}", true);
        }
    }

    /**
     * 更新编辑 订单
     * @param orderDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/order/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@Valid OrderDO orderDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            orderDO.setUpdateBy("sys");
            orderDO.setUpdateTime(new Date());
            Boolean res = orderService.updateByObj(orderDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "order update fail(更新失败)--"+orderDO.toString()+":{}", true);
        }
    }

    /**
     * 删除 订单
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/order/removeOrder", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse removeOrder(@RequestParam Long orderId) {
        if (ToolUtil.isEmpty(orderId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            OrderDO orderDO = new OrderDO();
            orderDO.setId(orderId);
            Boolean res = orderService.removeByObj(orderDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "order delete fail(删除失败)-- id:"+orderId+":{}", true);
        }
    }

    /**
     * 移动前端——下订单
     * add by mc 2018-4-29
     * @param account
     * @param userAddressId
     * @param cartIds 购物车id用“,”相隔的string
     * @param requirement
     * @return 返回 orderPayDO订单支付记录
     */
    @RequestMapping(value = "/wechat/order/placeOrder", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<OrderPayDO> placeOrder(@RequestParam String account, @RequestParam Long userAddressId, @RequestParam String cartIds, @RequestParam(required = false) String requirement) {
//        Map<String, Integer> productMaps = new HashMap<>();
        try {
            //解析所选产品jsonStr为map jsonStr为{1:8,2:10,3:12}
            //productMaps = (Map<String, Integer>) JSON.parse(productJsonStr);

            //检测accessToken是否失效
            BaseResponse<Boolean> checkToken = checkAccessToken();
            if (!checkToken.getResult()) {
                return new BaseResponse<>(checkToken.getCode(), checkToken.getMsg(), null);
            }

            //获取用户唯一标识 openid 用于微信下单
            String openid = "";
            if (StringUtils.isNotBlank(account)) {
                UserDO queryUser = new UserDO();
                queryUser.setId(Long.valueOf(account));
                UserDO userDO = userService.getOneByObj(queryUser);
                openid = userDO.getOpenid();
            }

            //获取用户地址
            UserAddressDO userAddressDO;
            if (userAddressId != null && userAddressId >0) {
                UserAddressDO query = new UserAddressDO();
                query.setId(userAddressId);
                query.setAccount(account);
                userAddressDO = userAddressService.getOneByObj(query);
            } else {
                return new BaseResponse(null);
            }
            //把cartIds变成cartIdArray
            Long [] cartIdArray = StrKit.idsStrToLongArray(cartIds);
            if (cartIdArray.length == 0) {
                return new BaseResponse(null);
            }
            OrderDO orderDO = new OrderDO();
            //获取唯一的订单号，线程安全
            String orderNo = NumberUtil.getUniqueOrderNo();
            orderDO.setOrderNo(orderNo);
            orderDO.setAccount(account);
            orderDO.setRequirement(requirement);
            Date orderCreateTime = new Date();
            orderDO.setCreateTime(orderCreateTime);
            //下订单 返回订单id
            OrderPayDO orderPayDO = orderService.placeOrderByObj(orderDO, cartIdArray, userAddressDO);
            if (orderPayDO != null && orderPayDO.getOrderId() > 0) {
                //把总额转换为 分
                String totalFee = String.valueOf(orderPayDO.getPayAmount().multiply(new BigDecimal(100)).intValue());
                //获取ip地址
                String payIp = HttpKit.getIpAddress();
                //去微信下统一订单
                Map<String, String> wxOrderInfo = doUnifiedOrder(orderNo, totalFee, Constant.DEFAULT_TRADE_TYPE, openid, DateUtil.formatDate(orderCreateTime, "yyyyMMddHHmmss"), payIp);
                //判断微信订单返回结果，保存在订单支付记录表
                orderPayDO.setOpenid(openid);
                orderPayDO.setSignType(Constant.DEFAULT_SIGN_TYPE);
                orderPayDO.setPayIp(payIp);
                return judgeUnifiedOrderResponse(wxOrderInfo, orderPayDO);
            } else {
                return new BaseResponse(null);
            }
        } catch (Exception e) {
            return handleException(e, "place order fail(下订单失败)-- account:"+account+"-- cartIds:"+cartIds+":{}", false);
        }
    }

    /**
     * 移动前端——重新下订单
     * add by mc 2018-5-22
     * @param orderId
     * @return orderPayDO订单支付记录
     */
    @RequestMapping(value = "/wechat/order/replaceOrder", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<OrderPayDO> replaceOrder(@RequestParam Long orderId) {
        try {
            //检测accessToken是否失效
            BaseResponse<Boolean> checkToken = checkAccessToken();
            if (!checkToken.getResult()) {
                return new BaseResponse<>(checkToken.getCode(), checkToken.getMsg(), null);
            }

            //首先去操作最近一次的订单支付记录
            List<OrderPayVO> orderPayVOList = orderPayService.getOrderPayVOByOrderId(orderId);
            Date orderCreateTime = new Date();
            String orderCreateTimeStr = DateUtil.formatDate(orderCreateTime, "yyyyMMddHHmmss");
            //获取ip地址
            String payIp = HttpKit.getIpAddress();
            if(orderPayVOList != null && orderPayVOList.size() > 0) {
                OrderPayVO orderPayVO = orderPayVOList.get(0);
                //把总额转换为 分
                String totalFee = String.valueOf(orderPayVO.getPayAmount().multiply(new BigDecimal(100)).intValue());
                //去微信下统一订单
                Map<String, String> wxOrderInfo = doUnifiedOrder(orderPayVO.getOrderNo(), totalFee, Constant.DEFAULT_TRADE_TYPE, orderPayVO.getOpenid(), orderCreateTimeStr, payIp);
                OrderPayDO orderPayDO = new OrderPayDO();
                orderPayDO.setOrderId(orderPayVO.getOrderId());
                orderPayDO.setAccount(orderPayVO.getAccount());
                orderPayDO.setPayAmount(orderPayVO.getPayAmount());
                orderPayDO.setPayType(orderPayVO.getPayType());
                orderPayDO.setOpenid(orderPayVO.getOpenid());
                orderPayDO.setSignType(orderPayVO.getSignType());
                orderPayDO.setPayIp(payIp);
                //返回预订单信息，保存在订单支付记录
                return judgeUnifiedOrderResponse(wxOrderInfo, orderPayDO);
            } else {
                //获取订单信息
                OrderDO queryOrder = new OrderDO();
                queryOrder.setId(orderId);
                OrderDO orderDO = orderService.getOneByObj(queryOrder);
                UserDO queryUser = new UserDO();
                queryUser.setId(Long.valueOf(orderDO.getAccount()));
                UserDO userDO = userService.getOneByObj(queryUser);
                //把总额转换为 分
                String totalFee = String.valueOf(orderDO.getAmount().multiply(new BigDecimal(100)).intValue());
                //去微信下统一订单
                Map<String, String> wxOrderInfo = doUnifiedOrder(orderDO.getOrderNo(), totalFee, Constant.DEFAULT_TRADE_TYPE, userDO.getOpenid(), orderCreateTimeStr, payIp);
                OrderPayDO orderPayDO = new OrderPayDO();
                orderPayDO.setOrderId(orderDO.getId());
                orderPayDO.setAccount(orderDO.getAccount());
                orderPayDO.setPayAmount(orderDO.getAmount());
                //支付类型跟order的支付类型一致
                orderPayDO.setPayType(orderDO.getPayType());
                orderPayDO.setOpenid(userDO.getOpenid());
                orderPayDO.setSignType(Constant.DEFAULT_SIGN_TYPE);
                orderPayDO.setPayIp(payIp);
                //返回预订单信息，保存在订单支付记录
                return judgeUnifiedOrderResponse(wxOrderInfo, orderPayDO);
            }
        } catch (Exception e) {
            return handleException(e, "replace order fail(重新下订单失败)-- id:"+orderId+":{}", false);
        }
    }

    /**
     * 根据微信统一下单返回的结果，保存在订单支付记录里
     * @param wxOrderInfo
     * @param orderPayDO
     * @return
     */
    private BaseResponse<OrderPayDO> judgeUnifiedOrderResponse(Map<String, String> wxOrderInfo, OrderPayDO orderPayDO) throws Exception {
        //判断返回失败，出现的错误信息
        String returnCode = wxOrderInfo.get("return_code");
        String returnMsg = wxOrderInfo.get("return_msg");
        //支付时间，创建时间，必须有才能入库
        Date payTime = new Date();
        orderPayDO.setCreateTime(payTime);
        if (Constant.WECHAT_FAIL.equals(returnCode) && StringUtils.isNotBlank(returnMsg)) {
            orderPayDO.setErrCode(returnCode);
            orderPayDO.setErrCodeDes(returnMsg);
            //保存在订单支付表
            orderPayService.saveByObj(orderPayDO);
            return new BaseResponse(Constant.FAIL_CODE, returnCode+","+returnMsg, null);
        }
        //判断返回成功，出现的错误信息
        String errCode = wxOrderInfo.get("err_code");
        String errCodeDes = wxOrderInfo.get("err_code_des");
        if (StringUtils.isNotBlank(errCode) && StringUtils.isNotBlank(errCodeDes)) {
            orderPayDO.setErrCode(errCode);
            orderPayDO.setErrCodeDes(errCodeDes);
            //保存在订单支付表
            orderPayService.saveByObj(orderPayDO);
            return new BaseResponse(Constant.FAIL_CODE, errCode+","+errCodeDes, null);
        }
        orderPayDO.setAppId(wxOrderInfo.get("appid"));
        orderPayDO.setTimeStamp(DateUtil.getLinuxTimeStamp(payTime));
        orderPayDO.setNonceStr(getNonceStr());
        orderPayDO.setPrepayId(wxOrderInfo.get("prepay_id"));
        //根据prepay_id和其他必要的参数再次生成签名传给前端
        orderPayDO.setPaySign(getSign(Constant.WECHAT_APPID, orderPayDO.getTimeStamp(), orderPayDO.getNonceStr(), wxOrderInfo.get("prepay_id"), orderPayDO.getSignType()));
        orderPayDO.setMchId(wxOrderInfo.get("mch_id"));
        orderPayDO.setDeviceInfo(wxOrderInfo.get("device_info"));
        orderPayDO.setTradeType(wxOrderInfo.get("trade_type"));
        //保存在订单支付表
        orderPayService.saveByObj(orderPayDO);
        //返回 订单支付记录 给前端，去支付
        return new BaseResponse(orderPayDO);
    }

    /**
     * 移动前端——根据订单id获取订单详情
     * add by mc 2018-5-8
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/wechat/order/getOrderDetailByOrderId", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<OrderDetailVO> getOrderDetailByOrderId(@RequestParam Long orderId) {
        try {
            //检测accessToken是否失效
            BaseResponse<Boolean> checkToken = checkAccessToken();
            if (!checkToken.getResult()) {
                return new BaseResponse<>(checkToken.getCode(), checkToken.getMsg(), null);
            }

            if (orderId != null && orderId > 0) {
                OrderDetailVO result = orderService.getOrderDetailByOrderId(orderId);
                return new BaseResponse<>(result);
            } else {
                return new BaseResponse<>(null);
            }
        } catch (Exception e) {
            return handleException(e, "get order detail fail(查询订单详情 失败)-- id:"+ orderId +":{}", false);
        }
    }

    /**
     * 移动前端——根据订单id删除订单及其下所有的订单项
     * add by mc 2018-5-23
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/wechat/order/remove", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Boolean> remove(@RequestParam Long orderId) {
        try {
            //检测accessToken是否失效
            BaseResponse<Boolean> checkToken = checkAccessToken();
            if (!checkToken.getResult()) {
                return checkToken;
            }

            if (orderId != null && orderId > 0) {
                //删除订单
                OrderDO orderDO = new OrderDO();
                orderDO.setId(orderId);
                //只能删除未完成的订单
                orderDO.setStatus(OrderStatus.UNDONE.getCode());
                Boolean orderResult = orderService.removeByObj(orderDO);
                //删除订单成功之后才能删除其订单项
                if(orderResult) {
                    //删除订单下的所有订单项
                    OrderItemDO orderItemDO = new OrderItemDO();
                    orderItemDO.setOrderId(orderId);
                    orderItemService.removeByObj(orderItemDO);
                }
                return BaseResponse.getRespByResultBool(orderResult);
            }
            return BaseResponse.getRespByResultBool(false);
        } catch (Exception e) {
            return handleException(e, "remove order and all order item fail(删除工单和其下的所有工单项 失败)-- id:"+ orderId +":{}", false);
        }
    }

    /**
     * 移动前端——分页查询订单
     * @param pageIndex
     * @param pageSize
     * @param account
     * @return
     */
    @RequestMapping(value = "/wechat/order/getPageList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<PageInfo<OrderDO>> getOrderPageList(@RequestParam Integer pageIndex, @RequestParam Integer pageSize, @RequestParam String account) {
        try {
            //检测accessToken是否失效
            BaseResponse<Boolean> checkToken = checkAccessToken();
            if (!checkToken.getResult()) {
                return new BaseResponse<>(checkToken.getCode(), checkToken.getMsg(), null);
            }

            PageInfo<OrderDO> result = orderService.getOrderDOPageList(pageIndex, pageSize, account);
            return new BaseResponse<>(result);
        } catch (Exception e) {
            return handleException(e, "get order page list fail(按条件查询 订单 列表失败)-- pageindex:"+ pageIndex + "-- pagesize:"+ pageSize + "-- account:"+ account +":{}", false);
        }
    }

}
