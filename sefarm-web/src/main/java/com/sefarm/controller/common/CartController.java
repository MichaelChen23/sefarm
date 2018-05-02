package com.sefarm.controller.common;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.constant.tips.ErrorTip;
import com.sefarm.common.constant.tips.Tip;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.common.vo.CartVO;
import com.sefarm.model.common.CartDO;
import com.sefarm.service.common.ICartService;
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
 * 购物车 控制器
 *
 * @author mc
 * @date 2018-5-1
 */
@Controller
@RequestMapping("/api/cart")
public class CartController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    private static String PREFIX = "/cart/";

    @Autowired
    ICartService cartService;

    /**
     * 跳转到查看 购物车列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cart.html";
    }

    /**
     * 跳转到新增 购物车列表的页面
     */
    @RequestMapping("/cart_save")
    public String saveView() {
        return PREFIX + "cart_save.html";
    }

    /**
     * 跳转到修改 购物车列表的页面
     */
    @RequestMapping("/cart_update/{cartId}")
    public String updateView(@PathVariable Long cartId, Model model) {
        if(ToolUtil.isEmpty(cartId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        CartVO cartVO = cartService.getCartVO(cartId);
        model.addAttribute(cartVO);
        return PREFIX + "cart_update.html";
    }

    /**
     * 按照查询条件查询 购物车产品 列表
     * @return
     */
    @RequestMapping(value = "/cart_list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<CartVO> getCartVOPageList(@RequestParam Integer pageIndex, @RequestParam Integer pageSize, @RequestParam(required = false) String sortStr, @RequestParam(required = false) String orderStr,
                                              @RequestParam(required = false) String account, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd) {
        try {
            PageInfo<CartVO> result = cartService.getCartVOPageList(pageIndex, pageSize, sortStr, orderStr, account, createTimeBegin, createTimeEnd);
            return result;
        } catch (Exception e) {
            logger.error("get cart page list fail(获取 购物车 分页 列表失败) -- :{}", e.getMessage());
            return null;
        }
    }

    /**
     * 添加产品 进 购物车
     * @param cartDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/saveCart", method = RequestMethod.POST)
    @ResponseBody
    public Tip save(@Valid CartDO cartDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            Boolean res = cartService.saveByObj(cartDO);
            if (res) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("cart save fail(保存失败)--"+cartDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 更新 购物车产品
     * @param cartDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/updateCart", method = RequestMethod.POST)
    @ResponseBody
    public Tip update(@Valid CartDO cartDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            if (cartDO != null) {
                Boolean res = cartService.updateByObj(cartDO);
                if (res) {
                    return SUCCESS_TIP;
                }
            }
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        } catch (Exception e) {
            logger.error("cart update fail(更新失败)--"+cartDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 删除 购物车产品
     * @param cartId
     * @return
     */
    @RequestMapping(value = "/removeCart", method = RequestMethod.POST)
    @ResponseBody
    public Tip remove(@RequestParam Long cartId) {
        if (ToolUtil.isEmpty(cartId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            CartDO cartDO = new CartDO();
            cartDO.setId(cartId);
            Boolean result = cartService.removeByObj(cartDO);
            if (result) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("cart delete fail(删除失败)--"+cartId+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 移动前端——根据用户帐号获取所有的购物车列表
     * @param account
     * @return
     */
    @RequestMapping(value = "/getAllListByAccount", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<CartVO>> getCartVOAllListByAccount(@RequestParam String account) {
        try {
            List<CartVO> list = cartService.getCartVOAllListByAccount(account);
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("cart get all list by account fail(获取所有的购物车列表失败)--"+account+":{}", e.getMessage());
            return new BaseResponse(null);
        }
    }

    /**
     * 移动前端——新增保存产品到购物车
     * @param account
     * @param productId
     * @param number
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Boolean> saveCartDO(@RequestParam String account, @RequestParam Long productId, @RequestParam Integer number) {
        try {
            CartDO cartDO = new CartDO();
            cartDO.setAccount(account);
            cartDO.setProductId(productId);
            cartDO.setNumber(number);
            cartDO.setCreateTime(new Date());
            Boolean result = cartService.saveByObj(cartDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("cart save fail(保存失败)--"+account+"--"+account+"--"+number+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    /**
     * 移动前端——更新编辑产品到购物车
     * @param cartId
     * @param number
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Boolean> updateCartDO(@RequestParam Long cartId, @RequestParam Integer number) {
        try {
            CartDO cartDO = new CartDO();
            cartDO.setId(cartId);
            cartDO.setNumber(number);
            cartDO.setUpdateTime(new Date());
            Boolean result = cartService.updateByObj(cartDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("cart update fail(更新失败)--"+cartId+"--"+number+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }
}