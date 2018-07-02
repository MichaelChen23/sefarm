package com.sefarm.controller.product;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.common.vo.ProductVO;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.product.ProductDO;
import com.sefarm.service.product.IProductService;
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
 * 产品的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@Controller
@RequestMapping("/api")
public class ProductController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private static String PREFIX = "/product/base/";

    @Autowired
    public IProductService productService;

    /**
     * 跳转到查看 产品 列表的页面
     */
    @RequestMapping("/prod")
    public String index() {
        return PREFIX + "product.html";
    }

    /**
     * 跳转到新增 产品 的页面
     */
    @RequestMapping("/prod/product_save")
    public String saveView() {
        return PREFIX + "product_save.html";
    }

    /**
     * 跳转到修改 产品 的页面
     */
    @RequestMapping("/prod/product_update/{prodId}")
    public String updateView(@PathVariable Long prodId, Model model) {
        if(ToolUtil.isEmpty(prodId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        //获取产品的信息
        ProductVO productVO = productService.getProductVO(prodId);
        model.addAttribute(productVO);
        return PREFIX + "product_update.html";
    }

    /**
     * 后台（用于首页搜索，产品页搜索，可根据各种条件排序） 按照查询条件查询 产品 列表
     * @return
     */
    @RequestMapping(value = "/prod/list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<ProductVO> getProductVOList(@RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize, @RequestParam String sortStr, @RequestParam String orderStr,
                                                @RequestParam(required = false) String name, @RequestParam(required = false) Long productTypeId, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd) {
        try {
            PageInfo<ProductVO> result = productService.getProductVOList(pageIndex, pageSize, sortStr, orderStr, name, productTypeId, createTimeBegin, createTimeEnd);
            return result;
        } catch (Exception e) {
            logger.error("get prod list fail(获取 产品 列表失败) -- :{}", e.getMessage());
            return null;
        }
    }

    /**
     * 添加 产品
     * @param productDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/prod/save", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse save(@Valid ProductDO productDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            //设置当前操作用户为创建人
            productDO.setCreateBy(getCurrentSysUser());
            productDO.setCreateTime(new Date());
            Boolean res = productService.saveByObj(productDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "prod save fail(保存失败)--"+productDO.toString()+":{}", true);
        }
    }

    /**
     * 更新编辑 产品
     * @param productDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/prod/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@Valid ProductDO productDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            //设置当前操作用户为更新人
            productDO.setUpdateBy(getCurrentSysUser());
            productDO.setUpdateTime(new Date());
            Boolean res = productService.updateByObj(productDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "prod update fail(更新失败)--"+productDO.toString()+":{}", true);
        }
    }

    /**
     * 删除 产品
     * @param prodId
     * @return
     */
    @RequestMapping(value = "/prod/remove", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse remove(@RequestParam Long prodId) {
        if (ToolUtil.isEmpty(prodId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            ProductDO productDO = new ProductDO();
            productDO.setId(prodId);
            Boolean res = productService.removeByObj(productDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "prod delete fail(删除失败)-- id:"+prodId+":{}", true);
        }
    }

    /**
     * 移动前端——根据产品类型id获取全部的产品List
     * add by mc 2018-4-24
     * @param typeId 产品类型id
     * @return
     */
    @RequestMapping(value = "/wechat/prod/getAllListByTypeId", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<ProductDO>> getAllProductListByTypeId(@RequestParam Long typeId) {
        try {
            List<ProductDO> list = productService.getProductListByTypeId(typeId);
            return new BaseResponse<>(list);
        } catch (Exception e) {
            return handleException(e, "product get all list by typeId (获取全部产品list失败)-- id:"+typeId+":{}", false);
        }
    }

    /**
     * 移动前端——（用于首页搜索，获取热门产品） 分页获取产品列表
     * @return
     */
    @RequestMapping(value = "/wechat/prod/searchPageList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<PageInfo<ProductDO>> searchProductPageList(@RequestParam Integer pageIndex, @RequestParam Integer pageSize,
                                                    @RequestParam(required = false) String name) {
        try {
            PageInfo<ProductDO> result = productService.searchProductDOPageList(pageIndex, pageSize, name);
            return new BaseResponse<>(result);
        } catch (Exception e) {
            return handleException(e, "search prod page list fail(搜索 产品 列表失败)-- pageIndex:"+pageIndex+"-- pageSize:"+pageSize+"-- name:"+name+":{}", false);
        }
    }

    /**
     * 移动前端—— 产品查询列表 按条件分页获取产品列表
     * add by mc 2018-4-26
     * @return
     */
    @RequestMapping(value = "/wechat/prod/getPageList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<PageInfo<ProductDO>> getProductPageList(@RequestParam Integer pageIndex, @RequestParam Integer pageSize, @RequestParam(required = false) Long typeId, @RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String newFlag, @RequestParam(required = false) String saleFlag, @RequestParam(required = false) String sortStr, @RequestParam(required = false) String orderStr) {
        try {
            PageInfo<ProductDO> result = productService.getProductDOPageList(pageIndex, pageSize, typeId, name, newFlag, saleFlag, sortStr, orderStr);
            return new BaseResponse<>(result);
        } catch (Exception e) {
            return handleException(e, "get prod page list fail(按条件查询 产品 列表失败)-- pageIndex:"+pageIndex+"-- pageSize:"+pageSize+"-- typeId:"+typeId+"-- name:"+name+"-- newFlag:"+newFlag+"-- saleFlag:"+saleFlag+"-- sortStr:"+sortStr+"-- orderStr:"+orderStr+":{}", false);
        }
    }

    /**
     * 移动前端——根据id获取产品信息
     * add by mc 2018-5-14
     * @param productId
     * @return
     */
    @RequestMapping(value = "/wechat/prod/getProductByProductId", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<ProductDO> getProductByProductId(@RequestParam Long productId) {
        try {
            ProductDO productDO = new ProductDO();
            productDO.setId(productId);
            ProductDO result = productService.getOneByObj(productDO);
            return new BaseResponse<>(result);
        } catch (Exception e) {
            return handleException(e, "get prod fail(根据id查询 产品信息 失败)-- productId:" + productId + ":{}", false);
        }
    }

}
