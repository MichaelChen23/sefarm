package com.sefarm.controller.product;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.common.vo.ProductCommentVO;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.order.OrderItemDO;
import com.sefarm.model.product.ProductCommentDO;
import com.sefarm.service.order.IOrderItemService;
import com.sefarm.service.product.IProductCommentService;
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
 * 产品评论的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@Controller
@RequestMapping("/api")
public class ProductCommentController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProductCommentController.class);

    private static String PREFIX = "/product/comment/";

    @Autowired
    public IProductCommentService productCommentService;

    @Autowired
    public IOrderItemService orderItemService;

    /**
     * 跳转到查看 产品评论 列表的页面
     */
    @RequestMapping("/prod-comment")
    public String index() {
        return PREFIX + "comment.html";
    }

    /**
     * 跳转到新增 产品评论 的页面
     */
    @RequestMapping("/prod-comment/comment_save")
    public String saveView() {
        return PREFIX + "comment_save.html";
    }

    /**
     * 跳转到修改 产品评论 的页面
     */
    @RequestMapping("/prod-comment/comment_update/{commentId}")
    public String updateView(@PathVariable Long commentId, Model model) {
        if(ToolUtil.isEmpty(commentId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        //获取产品评论的信息
        ProductCommentVO productCommentVO = productCommentService.getProductCommentVO(commentId);
        model.addAttribute(productCommentVO);
        return PREFIX + "comment_update.html";
    }

    /**
     * 按照查询条件查询 产品评论 列表
     * @return
     */
    @RequestMapping(value = "/prod-comment/list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<ProductCommentVO> getProductCommentVOList(@RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sortStr, @RequestParam(required = false) String orderStr, @RequestParam(required = false) String name,
                                                              @RequestParam(required = false) String productName, @RequestParam(required = false) String orderNo, @RequestParam(required = false) Integer stars, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd) {
        try {
            PageInfo<ProductCommentVO> result = productCommentService.getProductCommentVOList(pageIndex, pageSize, sortStr, orderStr, name, productName, orderNo, stars, createTimeBegin, createTimeEnd);
            return result;
        } catch (Exception e) {
            logger.error("get prod comment list fail(获取 产品评论 列表失败) -- :{}", e.getMessage());
            return null;
        }
    }

    /**
     * 添加 产品评论
     * @param productCommentDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/prod-comment/save", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse save(@Valid ProductCommentDO productCommentDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            productCommentDO.setCreateTime(new Date());
            Boolean res = productCommentService.saveByObj(productCommentDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "prod-comment save fail(保存失败)--"+productCommentDO.toString()+":{}", true);
        }
    }

    /**
     * 更新编辑 产品评论
     * @param productCommentDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/prod-comment/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@Valid ProductCommentDO productCommentDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            Boolean res = productCommentService.updateByObj(productCommentDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "prod-comment update fail(更新失败)--"+productCommentDO.toString()+":{}", true);
        }
    }

    /**
     * 删除 产品评论
     * @param commentId
     * @return
     */
    @RequestMapping(value = "/prod-comment/remove", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse remove(@RequestParam Long commentId) {
        if (ToolUtil.isEmpty(commentId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            ProductCommentDO productCommentDO = new ProductCommentDO();
            productCommentDO.setId(commentId);
            Boolean res = productCommentService.removeByObj(productCommentDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "prod-comment delete fail(删除失败)-- id:"+commentId+":{}", true);
        }
    }

    /**
     * 移动前端——根据产品id、订单id、星级数 分页获取 产品评论 列表
     * @param pageIndex
     * @param pageSize
     * @param productId
     * @param orderId
     * @param stars
     * @return
     */
    @RequestMapping(value = "/wechat/prod-comment/getPageList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<PageInfo<ProductCommentDO>> getProductCommentPageDOList(@RequestParam Integer pageIndex, @RequestParam Integer pageSize,
                                                                  @RequestParam(required = false) Long productId, @RequestParam(required = false) Long orderId, @RequestParam(required = false) Integer stars) {
        try {
            PageInfo<ProductCommentDO> result = productCommentService.getProductCommentPageDOList(pageIndex, pageSize, productId, orderId, stars);
            return new BaseResponse(result);
        } catch (Exception e) {
            return handleException(e, "get prod-comment page list fail(获取 产品评论 分页list 列表失败)-- pageIndex:"+pageIndex+"-- pageSize:"+pageSize+"-- productId:"+productId+"-- orderId:"+orderId+"-- stars:"+stars+":{}", false);
        }
    }

    /**
     * 移动前端——用户给产品评论评分
     * add by mc 2018-4-26
     * @param productId
     * @param orderId
     * @param account
     * @param name
     * @param content
     * @param star
     * @return
     */
    @RequestMapping(value = "/wechat/prod-comment/saveProductComment", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Boolean> saveProductCommentDO(@RequestParam Long productId, @RequestParam Long orderId, @RequestParam String account,
                                                      @RequestParam String name, @RequestParam String content, @RequestParam Integer star) {
        try {
            //检测accessToken是否失效
            BaseResponse<Boolean> checkToken = checkAccessToken();
            if (!checkToken.getResult()) {
                return checkToken;
            }

            ProductCommentDO productCommentDO = new ProductCommentDO();
            productCommentDO.setProductId(productId);
            productCommentDO.setOrderId(orderId);
            productCommentDO.setAccount(account);
            productCommentDO.setName(name);
            productCommentDO.setContent(content);
            productCommentDO.setStar(star);
            productCommentDO.setCreateTime(new Date());
            Boolean res = productCommentService.saveByObj(productCommentDO);
            //评论产品成功之后，再去改写订单项的已评论状态 add by mc 2018-5-24
            if (res) {
                OrderItemDO orderItemQuery = new OrderItemDO();
                orderItemQuery.setOrderId(orderId);
                orderItemQuery.setProductId(productId);
                OrderItemDO orderItemDO = orderItemService.getOneByObj(orderItemQuery);
                if (orderItemDO != null && orderItemDO.getId() > 0) {
                    //改为已评论
                    orderItemDO.setCommentFlag(Constant.STATUS_UNLOCK);
                    orderItemDO.setUpdateBy(account);
                    orderItemDO.setUpdateTime(new Date());
                    orderItemService.updateByObj(orderItemDO);
                }
            }
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "prod-comment save fail(保存失败)-- productId:"+productId+"-- orderId:"+orderId+"-- account:"+account+"-- name:"+name+"-- content:"+content+"-- star:"+star+":{}", false);
        }
    }

}
