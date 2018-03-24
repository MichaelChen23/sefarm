package com.sefarm.controller.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.model.product.ProductTypeDO;
import com.sefarm.service.product.IProductTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 产品类型的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@RestController
@RequestMapping("/prod-type")
public class ProductTypeController {

    private static final Logger logger = LoggerFactory.getLogger(ProductTypeController.class);

    @Reference(version = "1.0.0", timeout = 10000)
    public IProductTypeService productTypeService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResponse<Boolean> save(@RequestBody ProductTypeDO productTypeDO) {
        try {
            Boolean result = productTypeService.saveByObj(productTypeDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("prod-type save fail(保存失败)--"+productTypeDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public BaseResponse<Boolean> remove(@RequestBody ProductTypeDO productTypeDO) {//可通过id来删除，可通过其他条件是唯一性的来定位数据来删除，例如username是不相同，唯一的，就可以定位到唯一的数据
        try {
            Boolean result = productTypeService.removeByObj(productTypeDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("prod-type delete fail(删除失败)--"+productTypeDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
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

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResponse<Boolean> update(@RequestBody ProductTypeDO productTypeDO) {//一定要通过id来修改
        try {
            Boolean result = productTypeService.updateByObj(productTypeDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("prod-type update fail(更新失败)--"+productTypeDO.toString()+":{}", e.getMessage());
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

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public BaseResponse<List<ProductTypeDO>> getAll() {
        try {
            List<ProductTypeDO> list = productTypeService.getALL();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("prod-type get all(获取所有数据失败)-- :{}", e.getMessage());
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
