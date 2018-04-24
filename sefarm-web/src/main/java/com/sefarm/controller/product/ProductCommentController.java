package com.sefarm.controller.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.constant.tips.ErrorTip;
import com.sefarm.common.constant.tips.Tip;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.common.vo.ProductCommentVO;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.product.ProductCommentDO;
import com.sefarm.service.product.IProductCommentService;
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
 * 产品评论的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@Controller
@RequestMapping("/prod-comment")
public class ProductCommentController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProductCommentController.class);

    private static String PREFIX = "/product/comment/";

    @Reference(version = "1.0.0", timeout = Constant.DUBBO_TIME_OUT)
    public IProductCommentService productCommentService;

    /**
     * 跳转到查看 产品评论 列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "comment.html";
    }

    /**
     * 跳转到新增 产品评论 的页面
     */
    @RequestMapping("/comment_save")
    public String saveView() {
        return PREFIX + "comment_save.html";
    }

    /**
     * 跳转到修改 产品评论 的页面
     */
    @RequestMapping("/comment_update/{commentId}")
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
    @RequestMapping(value = "/list", method = RequestMethod.POST)
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
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Tip save(@Valid ProductCommentDO productCommentDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            productCommentDO.setCreateTime(new Date());
            Boolean res = productCommentService.saveByObj(productCommentDO);
            if (res) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("prod-comment save fail(保存失败)--"+productCommentDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 更新编辑 产品评论
     * @param productCommentDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Tip update(@Valid ProductCommentDO productCommentDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            if (productCommentDO != null) {
                Boolean res = productCommentService.updateByObj(productCommentDO);
                if (res) {
                    return SUCCESS_TIP;
                }
            }
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        } catch (Exception e) {
            logger.error("prod-comment update fail(更新失败)--"+productCommentDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 删除 产品评论
     * @param commentId
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public Tip remove(@RequestParam Long commentId) {
        if (ToolUtil.isEmpty(commentId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            ProductCommentDO productCommentDO = new ProductCommentDO();
            productCommentDO.setId(commentId);
            Boolean result = productCommentService.removeByObj(productCommentDO);
            if (result) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("prod-comment delete fail(删除失败)--"+commentId+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
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
    @RequestMapping(value = "/getPageList", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<ProductCommentDO> getProductCommentPageDOList(@RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize,
                                                                  @RequestParam(required = false) Long productId, @RequestParam(required = false) Long orderId, @RequestParam(required = false) Integer stars) {
        try {
            PageInfo<ProductCommentDO> result = productCommentService.getProductCommentPageDOList(pageIndex, pageSize, productId, orderId, stars);
            return result;
        } catch (Exception e) {
            logger.error("get prod-comment page list fail(获取 产品评论 分页list 列表失败) -- :{}", e.getMessage());
            return null;
        }
    }


    @RequestMapping(value = "/removeList", method = RequestMethod.POST)
    public BaseResponse<Boolean> removeList(@RequestBody String ids) {//批量删除
        try {
            List<String> list = JSON.parseArray(ids, String.class);
            Boolean result = productCommentService.batchRemoveByIds(list);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("prod-comment batch delete fail(批量删除失败)--"+ids+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public BaseResponse<ProductCommentDO> get(@RequestBody ProductCommentDO productCommentDO) {//可以通过id来查找，也可以同唯一性的条件来查找出唯一的数据，例如username是不相同，唯一的，就可以定位到唯一的数据
        ProductCommentDO result = null;
        try {
            result = (ProductCommentDO) productCommentService.getOneByObj(productCommentDO);
            return new BaseResponse<ProductCommentDO>(result);
        } catch (Exception e) {
            logger.error("prod-comment get fail(获取失败)--"+productCommentDO.toString()+":{}", e.getMessage());
            return new BaseResponse(result);
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public BaseResponse<List<ProductCommentDO>> getAll() {
        try {
            List<ProductCommentDO> list = productCommentService.getALL();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("prod-comment get all(获取所有数据失败)-- :{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Integer getCount(@RequestBody ProductCommentDO productCommentDO) {//输入为null，查询全部的数量，输入唯一性的字段，根据该字段数值查询唯一，数量为1
        try {
            Integer count = productCommentService.getCount(productCommentDO);
            return count;
        } catch (Exception e) {
            logger.error("prod-comment count fail(统计数目失败)--"+productCommentDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    /**
     * 根据输入字段和值，进行模糊查询
     * @param productCommentDO
     * @return
     * searchKey-查询的字段，searchValue-查询字段的值
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public PageInfo<ProductCommentDO> searchList(@RequestBody ProductCommentDO productCommentDO) {
        try {
            List<ProductCommentDO> list = productCommentService.searchListByKV(productCommentDO);
            return new PageInfo<ProductCommentDO>(list);
        } catch (Exception e) {
            logger.error("prod-comment search query fail(搜索查询失败)--"+productCommentDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

}
