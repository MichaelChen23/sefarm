package com.sefarm.controller.product;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.constant.tips.ErrorTip;
import com.sefarm.common.constant.tips.Tip;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.product.ProductCatalogDO;
import com.sefarm.service.product.IProductCatalogService;
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
 * 产品目录的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@Controller
@RequestMapping("/api/prod-cata")
public class ProductCatalogController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProductCatalogController.class);

    private static String PREFIX = "/product/catalog/";

    @Autowired
    public IProductCatalogService productCatalogService;

    /**
     * 跳转到查看 产品目录 列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "catalog.html";
    }

    /**
     * 跳转到新增 产品目录 的页面
     */
    @RequestMapping("/catalog_save")
    public String saveView() {
        return PREFIX + "catalog_save.html";
    }

    /**
     * 跳转到修改 产品目录 的页面
     */
    @RequestMapping("/catalog_update/{catalogId}")
    public String updateView(@PathVariable Long catalogId, Model model) {
        if(ToolUtil.isEmpty(catalogId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        ProductCatalogDO query = new ProductCatalogDO();
        query.setId(catalogId);
        ProductCatalogDO productCatalogDO = productCatalogService.getOneByObj(query);
        model.addAttribute(productCatalogDO);
        return PREFIX + "catalog_update.html";
    }

    /**
     * 按照查询条件查询 产品目录 列表
     * @return
     */
    @RequestMapping(value = "/catalog_list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<ProductCatalogDO> getProductCatalogDOList(@RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sortStr, @RequestParam(required = false) String orderStr,
                                                              @RequestParam(required = false) String name, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd) {
        try {
            PageInfo<ProductCatalogDO> result = productCatalogService.getProductCatalogDOList(pageIndex, pageSize, sortStr, orderStr, name, createTimeBegin, createTimeEnd);
            return result;
        } catch (Exception e) {
            logger.error("get catalog list fail(获取 产品目录 列表失败) -- :{}", e.getMessage());
            return null;
        }
    }

    /**
     * 新增 产品目录
     * @param productCatalogDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Tip save(@Valid ProductCatalogDO productCatalogDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            // 完善创建信息
            productCatalogDO.setCreateBy("sys");
            productCatalogDO.setCreateTime(new Date());
            Boolean res = productCatalogService.saveByObj(productCatalogDO);
            if (res) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("prod-cata save fail(保存失败)--"+productCatalogDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 更新编辑 产品目录
     * @param productCatalogDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Tip update(@Valid ProductCatalogDO productCatalogDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            if (productCatalogDO != null) {
                productCatalogDO.setUpdateBy("sys");
                productCatalogDO.setUpdateTime(new Date());
                Boolean res = productCatalogService.updateByObj(productCatalogDO);
                if (res) {
                    return SUCCESS_TIP;
                }
            }
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        } catch (Exception e) {
            logger.error("prod-cata update fail(更新失败)--"+productCatalogDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 删除 产品目录
     * @param catalogId
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public Tip remove(@RequestParam Long catalogId) {
        if (ToolUtil.isEmpty(catalogId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            ProductCatalogDO productCatalogDO = new ProductCatalogDO();
            productCatalogDO.setId(catalogId);
            Boolean result = productCatalogService.removeByObj(productCatalogDO);
            if (result) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("prod-cata delete fail(删除失败)--"+catalogId+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 移动前端——获取全部产品目录
     * add by mc 2018-4-24
     * @return
     */
    @RequestMapping(value = "/getAllList", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<ProductCatalogDO>> getAllProductCatalogList() {
        try {
            List<ProductCatalogDO> list = productCatalogService.getAllProductCatalogList();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("prod-cata get all list (获取所有产品目录list失败)-- :{}", e.getMessage());
            return new BaseResponse<>(null);
        }
    }



    @RequestMapping(value = "/removeList", method = RequestMethod.POST)
    public BaseResponse<Boolean> removeList(@RequestBody String ids) {//批量删除
        try {
            List<String> list = JSON.parseArray(ids, String.class);
            Boolean result = productCatalogService.batchRemoveByIds(list);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("prod-cata batch delete fail(批量删除失败)--"+ids+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public BaseResponse<ProductCatalogDO> get(@RequestBody ProductCatalogDO productCatalogDO) {//可以通过id来查找，也可以同唯一性的条件来查找出唯一的数据，例如username是不相同，唯一的，就可以定位到唯一的数据
        ProductCatalogDO result = null;
        try {
            result = (ProductCatalogDO) productCatalogService.getOneByObj(productCatalogDO);
            return new BaseResponse<ProductCatalogDO>(result);
        } catch (Exception e) {
            logger.error("prod-cata get fail(获取失败)--"+productCatalogDO.toString()+":{}", e.getMessage());
            return new BaseResponse(result);
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<ProductCatalogDO>> getAll() {
        try {
            List<ProductCatalogDO> list = productCatalogService.getALL();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("prod-cata get all(获取所有数据失败)-- :{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Integer getCount(@RequestBody ProductCatalogDO productCatalogDO) {//输入为null，查询全部的数量，输入唯一性的字段，根据该字段数值查询唯一，数量为1
        try {
            Integer count = productCatalogService.getCount(productCatalogDO);
            return count;
        } catch (Exception e) {
            logger.error("prod-cata count fail(统计数目失败)--"+productCatalogDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    /**
     * 根据输入字段和值，进行模糊查询
     * @param productCatalogDO
     * @return
     * searchKey-查询的字段，searchValue-查询字段的值
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public PageInfo<ProductCatalogDO> searchList(@RequestBody ProductCatalogDO productCatalogDO) {
        try {
            List<ProductCatalogDO> list = productCatalogService.searchListByKV(productCatalogDO);
            return new PageInfo<ProductCatalogDO>(list);
        } catch (Exception e) {
            logger.error("prod-cata search query fail(搜索查询失败)--"+productCatalogDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

}
