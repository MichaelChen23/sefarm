package com.sefarm.controller.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.model.product.ProductCommentDO;
import com.sefarm.service.product.IProductCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 产品评论的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@RestController
@RequestMapping("/prod-comment")
public class ProductCommentController {

    private static final Logger logger = LoggerFactory.getLogger(ProductCommentController.class);

    @Reference(version = "1.0.0", timeout = 10000)
    public IProductCommentService productCommentService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResponse<Boolean> save(@RequestBody ProductCommentDO productCommentDO) {
        try {
            Boolean result = productCommentService.saveByObj(productCommentDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("prod-comment save fail(保存失败)--"+productCommentDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public BaseResponse<Boolean> remove(@RequestBody ProductCommentDO productCommentDO) {//可通过id来删除，可通过其他条件是唯一性的来定位数据来删除，例如username是不相同，唯一的，就可以定位到唯一的数据
        try {
            Boolean result = productCommentService.removeByObj(productCommentDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("prod-comment delete fail(删除失败)--"+productCommentDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
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

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResponse<Boolean> update(@RequestBody ProductCommentDO productCommentDO) {//一定要通过id来修改
        try {
            Boolean result = productCommentService.updateByObj(productCommentDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("prod-comment update fail(更新失败)--"+productCommentDO.toString()+":{}", e.getMessage());
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

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageInfo<ProductCommentDO> getList(@RequestBody ProductCommentDO productCommentDO) {//通过输入page页数和rows每页查询的行数来查询lsit，如果不输入，默认值查询第一页；如果改用select（Obj）方法输入唯一性字段来查询会查到相关唯一的记录。
        try {
            List<ProductCommentDO> list = productCommentService.getListByObj(productCommentDO);
            return new PageInfo<ProductCommentDO>(list);
        } catch (Exception e) {
            logger.error("prod-comment get list fail(获取列表失败)--"+productCommentDO.toString()+":{}", e.getMessage());
            return null;
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
