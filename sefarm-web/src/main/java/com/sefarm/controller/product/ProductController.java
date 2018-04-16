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
import com.sefarm.common.vo.ProductVO;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.product.ProductDO;
import com.sefarm.service.product.IProductService;
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
 * 产品的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@Controller
@RequestMapping("/prod")
public class ProductController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private static String PREFIX = "/product/base/";

    @Reference(version = "1.0.0", timeout = 10000)
    public IProductService productService;

    /**
     * 跳转到查看 产品 列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "product.html";
    }

    /**
     * 跳转到新增 产品 的页面
     */
    @RequestMapping("/product_save")
    public String saveView() {
        return PREFIX + "product_save.html";
    }

    /**
     * 跳转到修改 产品 的页面
     */
    @RequestMapping("/product_update/{prodId}")
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
     * 按照查询条件查询 产品 列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<ProductVO> getProductVOList(@RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sortStr, @RequestParam(required = false) String orderStr,
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
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Tip save(@Valid ProductDO productDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            productDO.setCreateBy("sys");
            productDO.setCreateTime(new Date());
            Boolean res = productService.saveByObj(productDO);
            if (res) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("prod save fail(保存失败)--"+productDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 更新编辑 产品
     * @param productDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Tip update(@Valid ProductDO productDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            if (productDO != null) {
                productDO.setUpdateBy("sys");
                productDO.setUpdateTime(new Date());
                Boolean res = productService.updateByObj(productDO);
                if (res) {
                    return SUCCESS_TIP;
                }
            }
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        } catch (Exception e) {
            logger.error("prod update fail(更新失败)--"+productDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 删除 产品
     * @param prodId
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public Tip remove(@RequestParam Long prodId) {
        if (ToolUtil.isEmpty(prodId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            ProductDO productDO = new ProductDO();
            productDO.setId(prodId);
            Boolean result = productService.removeByObj(productDO);
            if (result) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("prod delete fail(删除失败)--"+prodId+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    @RequestMapping(value = "/removeList", method = RequestMethod.POST)
    public BaseResponse<Boolean> removeList(@RequestBody String ids) {//批量删除
        try {
            List<String> list = JSON.parseArray(ids, String.class);
            Boolean result = productService.batchRemoveByIds(list);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("prod batch delete fail(批量删除失败)--"+ids+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public BaseResponse<ProductDO> get(@RequestBody ProductDO productDO) {//可以通过id来查找，也可以同唯一性的条件来查找出唯一的数据，例如username是不相同，唯一的，就可以定位到唯一的数据
        ProductDO result = null;
        try {
            result = (ProductDO) productService.getOneByObj(productDO);
            return new BaseResponse<ProductDO>(result);
        } catch (Exception e) {
            logger.error("prod get fail(获取失败)--"+productDO.toString()+":{}", e.getMessage());
            return new BaseResponse(result);
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public BaseResponse<List<ProductDO>> getAll() {
        try {
            List<ProductDO> list = productService.getALL();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("prod get all(获取所有数据失败)-- :{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Integer getCount(@RequestBody ProductDO productDO) {//输入为null，查询全部的数量，输入唯一性的字段，根据该字段数值查询唯一，数量为1
        try {
            Integer count = productService.getCount(productDO);
            return count;
        } catch (Exception e) {
            logger.error("prod count fail(统计数目失败)--"+productDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    /**
     * 根据输入字段和值，进行模糊查询
     * @param productDO
     * @return
     * searchKey-查询的字段，searchValue-查询字段的值
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public PageInfo<ProductDO> searchList(@RequestBody ProductDO productDO) {
        try {
            List<ProductDO> list = productService.searchListByKV(productDO);
            return new PageInfo<ProductDO>(list);
        } catch (Exception e) {
            logger.error("prod search query fail(搜索查询失败)--"+productDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

}
