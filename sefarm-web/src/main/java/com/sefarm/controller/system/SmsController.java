package com.sefarm.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.model.system.SmsDO;
import com.sefarm.service.system.ISmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 短信的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    private static final Logger logger = LoggerFactory.getLogger(SmsController.class);

    @Reference(version = "1.0.0", timeout = 10000)
    public ISmsService smsService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResponse<Boolean> save(@RequestBody SmsDO smsDO) {
        try {
            Boolean result = smsService.saveByObj(smsDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("SMS save fail(保存失败)--"+smsDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public BaseResponse<Boolean> remove(@RequestBody SmsDO smsDO) {//可通过id来删除，可通过其他条件是唯一性的来定位数据来删除，例如username是不相同，唯一的，就可以定位到唯一的数据
        try {
            Boolean result = smsService.removeByObj(smsDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("SMS delete fail(删除失败)--"+smsDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/removeList", method = RequestMethod.POST)
    public BaseResponse<Boolean> removeList(@RequestBody String ids) {//批量删除
        try {
            List<String> list = JSON.parseArray(ids, String.class);
            Boolean result = smsService.batchRemoveByIds(list);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("SMS batch delete fail(批量删除失败)--"+ids+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResponse<Boolean> update(@RequestBody SmsDO smsDO) {//一定要通过id来修改
        try {
            Boolean result = smsService.updateByObj(smsDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("SMS update fail(更新失败)--"+smsDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public BaseResponse<SmsDO> get(@RequestBody SmsDO smsDO) {//可以通过id来查找，也可以同唯一性的条件来查找出唯一的数据，例如username是不相同，唯一的，就可以定位到唯一的数据
        SmsDO result = null;
        try {
            result = (SmsDO) smsService.getOneByObj(smsDO);
            return new BaseResponse<SmsDO>(result);
        } catch (Exception e) {
            logger.error("SMS get fail(获取失败)--"+smsDO.toString()+":{}", e.getMessage());
            return new BaseResponse(result);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageInfo<SmsDO> getList(@RequestBody SmsDO smsDO) {//通过输入page页数和rows每页查询的行数来查询lsit，如果不输入，默认值查询第一页；如果改用select（Obj）方法输入唯一性字段来查询会查到相关唯一的记录。
        try {
            List<SmsDO> list = smsService.getListByObj(smsDO);
            return new PageInfo<SmsDO>(list);
        } catch (Exception e) {
            logger.error("SMS get list fail(获取列表失败)--"+smsDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public BaseResponse<List<SmsDO>> getAll() {
        try {
            List<SmsDO> list = smsService.getALL();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("SMS get all(获取所有数据失败)-- :{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Integer getCount(@RequestBody SmsDO smsDO) {//输入为null，查询全部的数量，输入唯一性的字段，根据该字段数值查询唯一，数量为1
        try {
            Integer count = smsService.getCount(smsDO);
            return count;
        } catch (Exception e) {
            logger.error("SMS count fail(统计数目失败)--"+smsDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    /**
     * 根据输入字段和值，进行模糊查询
     * @param smsDO
     * @return
     * searchKey-查询的字段，searchValue-查询字段的值
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public PageInfo<SmsDO> searchList(@RequestBody SmsDO smsDO) {
        try {
            List<SmsDO> list = smsService.searchListByKV(smsDO);
            return new PageInfo<SmsDO>(list);
        } catch (Exception e) {
            logger.error("SMS search query fail(搜索查询失败)--"+smsDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

}
