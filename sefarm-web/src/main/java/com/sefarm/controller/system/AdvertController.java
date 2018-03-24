package com.sefarm.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.model.system.AdvertDO;
import com.sefarm.service.system.IAdvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 广告的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@RestController
@RequestMapping("/advert")
public class AdvertController {

    private static final Logger logger = LoggerFactory.getLogger(AdvertController.class);

    @Reference(version = "1.0.0", timeout = 10000)
    public IAdvertService advertService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResponse<Boolean> save(@RequestBody AdvertDO advertDO) {
        try {
            Boolean result = advertService.saveByObj(advertDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("advert save fail(保存失败)--"+advertDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public BaseResponse<Boolean> remove(@RequestBody AdvertDO advertDO) {//可通过id来删除，可通过其他条件是唯一性的来定位数据来删除，例如username是不相同，唯一的，就可以定位到唯一的数据
        try {
            Boolean result = advertService.removeByObj(advertDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("advert delete fail(删除失败)--"+advertDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/removeList", method = RequestMethod.POST)
    public BaseResponse<Boolean> removeList(@RequestBody String ids) {//批量删除
        try {
            List<String> list = JSON.parseArray(ids, String.class);
            Boolean result = advertService.batchRemoveByIds(list);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("advert batch delete fail(批量删除失败)--"+ids+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResponse<Boolean> update(@RequestBody AdvertDO advertDO) {//一定要通过id来修改
        try {
            Boolean result = advertService.updateByObj(advertDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("advert update fail(更新失败)--"+advertDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public BaseResponse<AdvertDO> get(@RequestBody AdvertDO advertDO) {//可以通过id来查找，也可以同唯一性的条件来查找出唯一的数据，例如username是不相同，唯一的，就可以定位到唯一的数据
        AdvertDO result = null;
        try {
            result = (AdvertDO) advertService.getOneByObj(advertDO);
            return new BaseResponse<AdvertDO>(result);
        } catch (Exception e) {
            logger.error("advert get fail(获取失败)--"+advertDO.toString()+":{}", e.getMessage());
            return new BaseResponse(result);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageInfo<AdvertDO> getList(@RequestBody AdvertDO advertDO) {//通过输入page页数和rows每页查询的行数来查询lsit，如果不输入，默认值查询第一页；如果改用select（Obj）方法输入唯一性字段来查询会查到相关唯一的记录。
        try {
            List<AdvertDO> list = advertService.getListByObj(advertDO);
            return new PageInfo<AdvertDO>(list);
        } catch (Exception e) {
            logger.error("advert get list fail(获取列表失败)--"+advertDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public BaseResponse<List<AdvertDO>> getAll() {
        try {
            List<AdvertDO> list = advertService.getALL();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("advert get all(获取所有数据失败)-- :{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Integer getCount(@RequestBody AdvertDO advertDO) {//输入为null，查询全部的数量，输入唯一性的字段，根据该字段数值查询唯一，数量为1
        try {
            Integer count = advertService.getCount(advertDO);
            return count;
        } catch (Exception e) {
            logger.error("advert count fail(统计数目失败)--"+advertDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    /**
     * 根据输入字段和值，进行模糊查询
     * @param advertDO
     * @return
     * searchKey-查询的字段，searchValue-查询字段的值
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public PageInfo<AdvertDO> searchList(@RequestBody AdvertDO advertDO) {
        try {
            List<AdvertDO> list = advertService.searchListByKV(advertDO);
            return new PageInfo<AdvertDO>(list);
        } catch (Exception e) {
            logger.error("advert search query fail(搜索查询失败)--"+advertDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

}
