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
import com.sefarm.common.vo.ProductTypeVO;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.product.ProductTypeDO;
import com.sefarm.service.product.IProductTypeService;
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
 * 产品类型的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@Controller
@RequestMapping("/prod-type")
public class ProductTypeController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProductTypeController.class);

    private static String PREFIX = "/product/type/";

    @Reference(version = "1.0.0", timeout = Constant.DUBBO_TIME_OUT)
    public IProductTypeService productTypeService;

    /**
     * 跳转到查看 产品类型 列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "type.html";
    }

    /**
     * 跳转到新增 产品类型 的页面
     */
    @RequestMapping("/type_save")
    public String saveView() {
        return PREFIX + "type_save.html";
    }

    /**
     * 跳转到修改 产品类型 的页面
     */
    @RequestMapping("/type_update/{typeId}")
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
    @RequestMapping(value = "/type_list", method = RequestMethod.POST)
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
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Tip save(@Valid ProductTypeDO productTypeDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            productTypeDO.setCreateBy("sys");
            productTypeDO.setCreateTime(new Date());
            Boolean res = productTypeService.saveByObj(productTypeDO);
            if (res) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("prod-type save fail(保存失败)--"+productTypeDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 更新编辑 产品类型
     * @param productTypeDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Tip update(@Valid ProductTypeDO productTypeDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            if (productTypeDO != null) {
                productTypeDO.setUpdateBy("sys");
                productTypeDO.setUpdateTime(new Date());
                Boolean res = productTypeService.updateByObj(productTypeDO);
                if (res) {
                    return SUCCESS_TIP;
                }
            }
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        } catch (Exception e) {
            logger.error("prod-type update fail(更新失败)--"+productTypeDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 删除 产品类型
     * @param typeId
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public Tip remove(@RequestParam Long typeId) {//可通过id来删除，可通过其他条件是唯一性的来定位数据来删除，例如username是不相同，唯一的，就可以定位到唯一的数据
        if (ToolUtil.isEmpty(typeId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            ProductTypeDO productTypeDO = new ProductTypeDO();
            productTypeDO.setId(typeId);
            Boolean result = productTypeService.removeByObj(productTypeDO);
            if (result) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("prod-type delete fail(删除失败)--"+typeId+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 获取所有的产品类型list 后台select使用
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<ProductTypeDO>> getAll() {
        try {
            List<ProductTypeDO> list = productTypeService.getALL();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("prod-type get all(获取所有数据失败)-- :{}", e.getMessage());
            return null;
        }
    }

    /**
     * 移动前端——根据产品目录id获取全部的产品类型
     * add by mc 2018-4-24
     * @param catalogId 产品目录id
     * @return
     */
    @RequestMapping(value = "/getAllListByCatalogId", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<ProductTypeDO>> getAllProductTypeListByCatalogId(@RequestParam Long catalogId) {
        try {
            List<ProductTypeDO> list = productTypeService.getProductTypeListByCatalogId(catalogId);
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("prod-type get all list by catalogid (获取全部产品类型list失败)-- :{}", e.getMessage());
            return null;
        }
    }


    @RequestMapping(value = "/removeList", method = RequestMethod.POST)
    public BaseResponse<Boolean> removeList(@RequestBody String ids) {//批量删除
        try {
            List<String> list = JSON.parseArray(ids, String.class);
            Boolean result = productTypeService.batchRemoveByIds(list);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("prod-type batch delete fail(批量删除失败)--"+ids+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public BaseResponse<ProductTypeDO> get(@RequestBody ProductTypeDO productTypeDO) {//可以通过id来查找，也可以同唯一性的条件来查找出唯一的数据，例如username是不相同，唯一的，就可以定位到唯一的数据
        ProductTypeDO result = null;
        try {
            result = (ProductTypeDO) productTypeService.getOneByObj(productTypeDO);
            return new BaseResponse<ProductTypeDO>(result);
        } catch (Exception e) {
            logger.error("prod-type get fail(获取失败)--"+productTypeDO.toString()+":{}", e.getMessage());
            return new BaseResponse(result);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageInfo<ProductTypeDO> getList(@RequestBody ProductTypeDO productTypeDO) {//通过输入page页数和rows每页查询的行数来查询lsit，如果不输入，默认值查询第一页；如果改用select（Obj）方法输入唯一性字段来查询会查到相关唯一的记录。
        try {
            List<ProductTypeDO> list = productTypeService.getListByObj(productTypeDO);
            return new PageInfo<ProductTypeDO>(list);
        } catch (Exception e) {
            logger.error("prod-type get list fail(获取列表失败)--"+productTypeDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Integer getCount(@RequestBody ProductTypeDO productTypeDO) {//输入为null，查询全部的数量，输入唯一性的字段，根据该字段数值查询唯一，数量为1
        try {
            Integer count = productTypeService.getCount(productTypeDO);
            return count;
        } catch (Exception e) {
            logger.error("prod-type count fail(统计数目失败)--"+productTypeDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    /**
     * 根据输入字段和值，进行模糊查询
     * @param productTypeDO
     * @return
     * searchKey-查询的字段，searchValue-查询字段的值
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public PageInfo<ProductTypeDO> searchList(@RequestBody ProductTypeDO productTypeDO) {
        try {
            List<ProductTypeDO> list = productTypeService.searchListByKV(productTypeDO);
            return new PageInfo<ProductTypeDO>(list);
        } catch (Exception e) {
            logger.error("prod-type search query fail(搜索查询失败)--"+productTypeDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

}
