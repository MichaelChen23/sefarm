package com.sefarm.controller.product;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.common.vo.ProductTypeVO;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.product.ProductTypeDO;
import com.sefarm.service.product.IProductTypeService;
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
 * 产品类型的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@Controller
@RequestMapping("/api")
public class ProductTypeController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProductTypeController.class);

    private static String PREFIX = "/product/type/";

    @Autowired
    public IProductTypeService productTypeService;

    /**
     * 跳转到查看 产品类型 列表的页面
     */
    @RequestMapping("/prod-type")
    public String index() {
        return PREFIX + "type.html";
    }

    /**
     * 跳转到新增 产品类型 的页面
     */
    @RequestMapping("/prod-type/type_save")
    public String saveView() {
        return PREFIX + "type_save.html";
    }

    /**
     * 跳转到修改 产品类型 的页面
     */
    @RequestMapping("/prod-type/type_update/{typeId}")
    public String updateView(@PathVariable Long typeId, Model model) {
        if(ToolUtil.isEmpty(typeId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        //获取产品类型的信息
        ProductTypeVO productTypeVO = productTypeService.getProductTypeVO(typeId);
        model.addAttribute(productTypeVO);
        return PREFIX + "type_update.html";
    }

    /**
     * 按照查询条件查询 产品类型 列表
     * @return
     */
    @RequestMapping(value = "/prod-type/type_list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<ProductTypeVO> getProductTypeVOList(@RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sortStr, @RequestParam(required = false) String orderStr,
                                              @RequestParam(required = false) String name, @RequestParam(required = false) Long productCatalogId, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd) {
        try {
            PageInfo<ProductTypeVO> result = productTypeService.getProductTypeVOList(pageIndex, pageSize, sortStr, orderStr, name, productCatalogId, createTimeBegin, createTimeEnd);
            return result;
        } catch (Exception e) {
            logger.error("get prod-type list fail(获取 产品类型 列表失败) -- :{}", e.getMessage());
            return null;
        }
    }

    /**
     * 添加 产品类型
     * @param productTypeDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/prod-type/save", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse save(@Valid ProductTypeDO productTypeDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            //设置当前操作用户为创建人
            productTypeDO.setCreateBy(getCurrentSysUser());
            productTypeDO.setCreateTime(new Date());
            Boolean res = productTypeService.saveByObj(productTypeDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "prod-type save fail(保存失败)--"+productTypeDO.toString()+":{}", true);
        }
    }

    /**
     * 更新编辑 产品类型
     * @param productTypeDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/prod-type/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@Valid ProductTypeDO productTypeDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            //设置当前操作用户为更新人
            productTypeDO.setUpdateBy(getCurrentSysUser());
            productTypeDO.setUpdateTime(new Date());
            Boolean res = productTypeService.updateByObj(productTypeDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "prod-type update fail(更新失败)--"+productTypeDO.toString()+":{}", true);
        }
    }

    /**
     * 删除 产品类型
     * @param typeId
     * @return
     */
    @RequestMapping(value = "/prod-type/remove", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse remove(@RequestParam Long typeId) {//可通过id来删除，可通过其他条件是唯一性的来定位数据来删除，例如username是不相同，唯一的，就可以定位到唯一的数据
        if (ToolUtil.isEmpty(typeId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            ProductTypeDO productTypeDO = new ProductTypeDO();
            productTypeDO.setId(typeId);
            Boolean res = productTypeService.removeByObj(productTypeDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "prod-type delete fail(删除失败)-- id:"+typeId+":{}", true);
        }
    }

    /**
     * 获取所有的产品类型list 后台select使用
     * @return
     */
    @RequestMapping(value = "/prod-type/getAll", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<ProductTypeDO>> getAll() {
        try {
            List<ProductTypeDO> list = productTypeService.getALL();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            return handleException(e, "prod-type get all(获取所有数据失败)-- :{}", true);
        }
    }

    /**
     * 移动前端——根据产品目录id获取全部的产品类型
     * add by mc 2018-4-24
     * @param catalogId 产品目录id
     * @return
     */
    @RequestMapping(value = "/wechat/prod-type/getAllListByCatalogId", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<ProductTypeDO>> getAllProductTypeListByCatalogId(@RequestParam Long catalogId) {
        try {
            List<ProductTypeDO> list = productTypeService.getProductTypeListByCatalogId(catalogId);
            return new BaseResponse<>(list);
        } catch (Exception e) {
            return handleException(e, "prod-type get all list by catalogid (获取全部产品类型list失败)-- id:"+catalogId+":{}", false);
        }
    }

}
