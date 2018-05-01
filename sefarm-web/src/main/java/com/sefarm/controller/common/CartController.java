package com.sefarm.controller.common;

import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.vo.CartVO;
import com.sefarm.model.common.CartDO;
import com.sefarm.service.common.ICartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    ICartService cartService;

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
