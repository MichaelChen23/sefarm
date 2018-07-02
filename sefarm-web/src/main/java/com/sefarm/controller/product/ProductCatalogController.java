package com.sefarm.controller.product;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseResponse;
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
@RequestMapping("/api")
public class ProductCatalogController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProductCatalogController.class);

    private static String PREFIX = "/product/catalog/";

    @Autowired
    public IProductCatalogService productCatalogService;

    /**
     * 跳转到查看 产品目录 列表的页面
     */
    @RequestMapping("/prod-cata")
    public String index() {
        return PREFIX + "catalog.html";
    }

    /**
     * 跳转到新增 产品目录 的页面
     */
    @RequestMapping("/prod-cata/catalog_save")
    public String saveView() {
        return PREFIX + "catalog_save.html";
    }

    /**
     * 跳转到修改 产品目录 的页面
     */
    @RequestMapping("/prod-cata/catalog_update/{catalogId}")
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
    @RequestMapping(value = "/prod-cata/catalog_list", method = RequestMethod.POST)
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
    @RequestMapping(value = "/prod-cata/save", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse save(@Valid ProductCatalogDO productCatalogDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            // 完善创建信息，把当前操作用户当创建人
            productCatalogDO.setCreateBy(getCurrentSysUser());
            productCatalogDO.setCreateTime(new Date());
            Boolean res = productCatalogService.saveByObj(productCatalogDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "prod-cata save fail(保存失败)--"+productCatalogDO.toString()+":{}", true);
        }
    }

    /**
     * 更新编辑 产品目录
     * @param productCatalogDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/prod-cata/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@Valid ProductCatalogDO productCatalogDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            //设置当前操作用户为更新人
            productCatalogDO.setUpdateBy(getCurrentSysUser());
            productCatalogDO.setUpdateTime(new Date());
            Boolean res = productCatalogService.updateByObj(productCatalogDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "prod-cata update fail(更新失败)--"+productCatalogDO.toString()+":{}", true);
        }
    }

    /**
     * 删除 产品目录
     * @param catalogId
     * @return
     */
    @RequestMapping(value = "/prod-cata/remove", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse remove(@RequestParam Long catalogId) {
        if (ToolUtil.isEmpty(catalogId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            ProductCatalogDO productCatalogDO = new ProductCatalogDO();
            productCatalogDO.setId(catalogId);
            Boolean res = productCatalogService.removeByObj(productCatalogDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "prod-cata delete fail(删除失败)-- id:"+catalogId+":{}", true);
        }
    }

    /**
     * 移动前端——获取全部产品目录
     * add by mc 2018-4-24
     * @return
     */
    @RequestMapping(value = "/wechat/prod-cata/getAllList", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<ProductCatalogDO>> getAllProductCatalogList() {
        try {
            List<ProductCatalogDO> list = productCatalogService.getAllProductCatalogList();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            return handleException(e, "prod-cata get all list (获取所有产品目录list失败)-- :{}", false);
        }
    }

}
