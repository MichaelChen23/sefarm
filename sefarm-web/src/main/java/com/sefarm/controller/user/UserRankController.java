package com.sefarm.controller.user;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.model.user.UserRankDO;
import com.sefarm.service.user.IUserRankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户等级的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@RestController
@RequestMapping("/user-rank")
public class UserRankController {

    private static final Logger logger = LoggerFactory.getLogger(UserRankController.class);

//    @Reference(version = "1.0.0", timeout = Constant.DUBBO_TIME_OUT)
    public IUserRankService userRankService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResponse<Boolean> save(@RequestBody UserRankDO userRankDO) {
        try {
            Boolean result = userRankService.saveByObj(userRankDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("user-rank save fail(保存失败)--"+userRankDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public BaseResponse<Boolean> remove(@RequestBody UserRankDO userRankDO) {//可通过id来删除，可通过其他条件是唯一性的来定位数据来删除，例如username是不相同，唯一的，就可以定位到唯一的数据
        try {
            Boolean result = userRankService.removeByObj(userRankDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("user-rank delete fail(删除失败)--"+userRankDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/removeList", method = RequestMethod.POST)
    public BaseResponse<Boolean> removeList(@RequestBody String ids) {//批量删除
        try {
            List<String> list = JSON.parseArray(ids, String.class);
            Boolean result = userRankService.batchRemoveByIds(list);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("user-rank batch delete fail(批量删除失败)--"+ids+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResponse<Boolean> update(@RequestBody UserRankDO userRankDO) {//一定要通过id来修改
        try {
            Boolean result = userRankService.updateByObj(userRankDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("user-rank update fail(更新失败)--"+userRankDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public BaseResponse<UserRankDO> get(@RequestBody UserRankDO userRankDO) {//可以通过id来查找，也可以同唯一性的条件来查找出唯一的数据，例如username是不相同，唯一的，就可以定位到唯一的数据
        UserRankDO result = null;
        try {
            result = (UserRankDO) userRankService.getOneByObj(userRankDO);
            return new BaseResponse<UserRankDO>(result);
        } catch (Exception e) {
            logger.error("user-rank get fail(获取失败)--"+userRankDO.toString()+":{}", e.getMessage());
            return new BaseResponse(result);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageInfo<UserRankDO> getList(@RequestBody UserRankDO userRankDO) {//通过输入page页数和rows每页查询的行数来查询lsit，如果不输入，默认值查询第一页；如果改用select（Obj）方法输入唯一性字段来查询会查到相关唯一的记录。
        try {
            List<UserRankDO> list = userRankService.getListByObj(userRankDO);
            return new PageInfo<UserRankDO>(list);
        } catch (Exception e) {
            logger.error("user-rank get list fail(获取列表失败)--"+userRankDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public BaseResponse<List<UserRankDO>> getAll() {
        try {
            List<UserRankDO> list = userRankService.getALL();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("user-rank get all(获取所有数据失败)-- :{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Integer getCount(@RequestBody UserRankDO userRankDO) {//输入为null，查询全部的数量，输入唯一性的字段，根据该字段数值查询唯一，数量为1
        try {
            Integer count = userRankService.getCount(userRankDO);
            return count;
        } catch (Exception e) {
            logger.error("user-rank count fail(统计数目失败)--"+userRankDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    /**
     * 根据输入字段和值，进行模糊查询
     * @param userRankDO
     * @return
     * searchKey-查询的字段，searchValue-查询字段的值
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public PageInfo<UserRankDO> searchList(@RequestBody UserRankDO userRankDO) {
        try {
            List<UserRankDO> list = userRankService.searchListByKV(userRankDO);
            return new PageInfo<UserRankDO>(list);
        } catch (Exception e) {
            logger.error("user-rank search query fail(搜索查询失败)--"+userRankDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

}
