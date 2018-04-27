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
import com.sefarm.common.vo.OrderItemVO;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.order.OrderItemDO;
import com.sefarm.service.order.IOrderItemService;
import com.sefarm.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Reference(version = "1.0.0", timeout = Constant.DUBBO_TIME_OUT)
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
    public Tip save(@Valid OrderItemDO orderItemDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            Boolean res = orderItemService.saveByObj(orderItemDO);
            if (res) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("order-item save fail(保存失败)--"+orderItemDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
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
    public Tip update(@Valid OrderItemDO orderItemDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            if (orderItemDO != null) {
                orderItemDO.setUpdateBy("sys");
                orderItemDO.setUpdateTime(new Date());
                Boolean res = orderItemService.updateByObj(orderItemDO);
                if (res) {
                    return SUCCESS_TIP;
                }
            }
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        } catch (Exception e) {
            logger.error("order-item update fail(更新失败)--"+orderItemDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 删除 订单项
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public Tip remove(@RequestParam Long itemId) {
        if (ToolUtil.isEmpty(itemId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            OrderItemDO orderItemDO = new OrderItemDO();
            orderItemDO.setId(itemId);
            Boolean result = orderItemService.removeByObj(orderItemDO);
            if (result) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("order-item delete fail(删除失败)--"+itemId+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    @RequestMapping(value = "/removeList", method = RequestMethod.POST)
    public BaseResponse<Boolean> removeList(@RequestBody String ids) {//批量删除
        try {
            List<String> list = JSON.parseArray(ids, String.class);
            Boolean result = orderItemService.batchRemoveByIds(list);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("order-item batch delete fail(批量删除失败)--"+ids+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public BaseResponse<OrderItemDO> get(@RequestBody OrderItemDO orderItemDO) {//可以通过id来查找，也可以同唯一性的条件来查找出唯一的数据，例如username是不相同，唯一的，就可以定位到唯一的数据
        OrderItemDO result = null;
        try {
            result = (OrderItemDO) orderItemService.getOneByObj(orderItemDO);
            return new BaseResponse<OrderItemDO>(result);
        } catch (Exception e) {
            logger.error("order-item get fail(获取失败)--"+orderItemDO.toString()+":{}", e.getMessage());
            return new BaseResponse(result);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageInfo<OrderItemDO> getList(@RequestBody OrderItemDO orderItemDO) {//通过输入page页数和rows每页查询的行数来查询lsit，如果不输入，默认值查询第一页；如果改用select（Obj）方法输入唯一性字段来查询会查到相关唯一的记录。
        try {
            List<OrderItemDO> list = orderItemService.getListByObj(orderItemDO);
            return new PageInfo<OrderItemDO>(list);
        } catch (Exception e) {
            logger.error("order-item get list fail(获取列表失败)--"+orderItemDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public BaseResponse<List<OrderItemDO>> getAll() {
        try {
            List<OrderItemDO> list = orderItemService.getALL();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("order-item get all(获取所有数据失败)-- :{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Integer getCount(@RequestBody OrderItemDO orderItemDO) {//输入为null，查询全部的数量，输入唯一性的字段，根据该字段数值查询唯一，数量为1
        try {
            Integer count = orderItemService.getCount(orderItemDO);
            return count;
        } catch (Exception e) {
            logger.error("order-item count fail(统计数目失败)--"+orderItemDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    /**
     * 根据输入字段和值，进行模糊查询
     * @param orderItemDO
     * @return
     * searchKey-查询的字段，searchValue-查询字段的值
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public PageInfo<OrderItemDO> searchList(@RequestBody OrderItemDO orderItemDO) {
        try {
            List<OrderItemDO> list = orderItemService.searchListByKV(orderItemDO);
            return new PageInfo<OrderItemDO>(list);
        } catch (Exception e) {
            logger.error("order-item search query fail(搜索查询失败)--"+orderItemDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

}
