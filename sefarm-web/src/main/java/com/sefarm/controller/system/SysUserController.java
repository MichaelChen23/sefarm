package com.sefarm.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.vo.SysUserVO;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.system.SysUserDO;
import com.sefarm.service.system.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统用户的Controller
 *
 * @author mc
 * @date 2018-3-18
 */
@Controller//controller对应beetl模版链接，restcontroller对应所有接口以restful方式返回结果，如果不用restController则要对每个接口使用@ResponseBody，两者只能选其一
@RequestMapping("/sys-user")
public class SysUserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    /**
     * 网页路径的前缀
     */
    private static String PREFIX = "/system/sysuser/";

    @Reference(version = "1.0.0", timeout = 10000)
    public ISysUserService sysUserService;


    /**
     * 跳转到查看系统用户列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "sysuser.html";
    }

    /**
     * 跳转到新增系统用户的页面
     */
    @RequestMapping("/sysuser_save")
    public String saveView() {
        return PREFIX + "sysuser_save.html";
    }

    /**
     * 按照查询条件查询系统用户列表
     * @return
     */
    @RequestMapping(value = "/sysuser_list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<SysUserVO> getSysUserList(@RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sortStr, @RequestParam(required = false) String orderStr,
                                                @RequestParam(required = false) String name, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd) {
        try {
            PageInfo<SysUserVO> result = sysUserService.getSysUserVOList(pageIndex, pageSize, sortStr, orderStr, name, createTimeBegin, createTimeEnd);
            return result;
        } catch (Exception e) {
            logger.error("get sys-user list fail(获取系统用户列表失败) -- :{}", e.getMessage());
            return null;
        }
    }



    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResponse<Boolean> save(@RequestBody SysUserDO sysUserDO) {
        try {
            Boolean result = sysUserService.saveByObj(sysUserDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("sys-user save fail(保存失败)--"+sysUserDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public BaseResponse<Boolean> remove(@RequestBody SysUserDO sysUserDO) {//可通过id来删除，可通过其他条件是唯一性的来定位数据来删除，例如username是不相同，唯一的，就可以定位到唯一的数据
        try {
            Boolean result = sysUserService.removeByObj(sysUserDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("sys-user delete fail(删除失败)--"+sysUserDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/removeList", method = RequestMethod.POST)
    public BaseResponse<Boolean> removeList(@RequestBody String ids) {//批量删除
        try {
            List<String> list = JSON.parseArray(ids, String.class);
            Boolean result = sysUserService.batchRemoveByIds(list);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("sys-user batch delete fail(批量删除失败)--"+ids+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResponse<Boolean> update(@RequestBody SysUserDO sysUserDO) {//一定要通过id来修改
        try {
            Boolean result = sysUserService.updateByObj(sysUserDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("sys-user update fail(更新失败)--"+sysUserDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public BaseResponse<SysUserDO> get(@RequestBody SysUserDO sysUserDO) {//可以通过id来查找，也可以同唯一性的条件来查找出唯一的数据，例如username是不相同，唯一的，就可以定位到唯一的数据
        SysUserDO result = null;
        try {
            result = (SysUserDO) sysUserService.getOneByObj(sysUserDO);
            return new BaseResponse<SysUserDO>(result);
        } catch (Exception e) {
            logger.error("sys-user get fail(获取失败)--"+sysUserDO.toString()+":{}", e.getMessage());
            return new BaseResponse(result);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public List<SysUserDO> getList(@RequestBody SysUserDO sysUserDO) {//通过输入page页数和rows每页查询的行数来查询lsit，如果不输入，默认值查询第一页；如果改用select（Obj）方法输入唯一性字段来查询会查到相关唯一的记录。
        try {
            List<SysUserDO> list = sysUserService.getListByObj(sysUserDO);
            return list;
        } catch (Exception e) {
            logger.error("sys-user get list fail(获取列表失败)--"+sysUserDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public BaseResponse<List<SysUserDO>> getAll() {
        try {
            List<SysUserDO> list = sysUserService.getALL();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("sys-user get all(获取所有数据失败)-- :{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Integer getCount(@RequestBody SysUserDO sysUserDO) {//输入为null，查询全部的数量，输入唯一性的字段，根据该字段数值查询唯一，数量为1
        try {
            Integer count = sysUserService.getCount(sysUserDO);
            return count;
        } catch (Exception e) {
            logger.error("sys-user count fail(统计数目失败)--"+sysUserDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    /**
     * 根据输入字段和值，进行模糊查询
     * @param sysUserDO
     * @return
     * searchKey-查询的字段，searchValue-查询字段的值
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public PageInfo<SysUserDO> searchList(@RequestBody SysUserDO sysUserDO) {
        try {
            List<SysUserDO> list = sysUserService.searchListByKV(sysUserDO);
            return new PageInfo<SysUserDO>(list);
        } catch (Exception e) {
            logger.error("sys-user search query fail(搜索查询失败)--"+sysUserDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

}
