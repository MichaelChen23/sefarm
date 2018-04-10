package com.sefarm.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.node.ZTreeNode;
import com.sefarm.model.system.SysRoleDO;
import com.sefarm.service.system.ISysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统角色的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@RestController
@RequestMapping("/sys-role")
public class SysRoleController {

    private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Reference(version = "1.0.0", timeout = 10000)
    public ISysRoleService sysRoleService;


    @RequestMapping(value = "/sysRoleTree")
    @ResponseBody
    public List<ZTreeNode> getSysRoleTree() {
        List<ZTreeNode> sysRoleTree = sysRoleService.getSysRoleTree();
        sysRoleTree.add(ZTreeNode.createParent());
        return sysRoleTree;
    }



    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResponse<Boolean> save(@RequestBody SysRoleDO sysRoleDO) {
        try {
            Boolean result = sysRoleService.saveByObj(sysRoleDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("sys-role save fail(保存失败)--"+sysRoleDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public BaseResponse<Boolean> remove(@RequestBody SysRoleDO sysRoleDO) {//可通过id来删除，可通过其他条件是唯一性的来定位数据来删除，例如username是不相同，唯一的，就可以定位到唯一的数据
        try {
            Boolean result = sysRoleService.removeByObj(sysRoleDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("sys-role delete fail(删除失败)--"+sysRoleDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/removeList", method = RequestMethod.POST)
    public BaseResponse<Boolean> removeList(@RequestBody String ids) {//批量删除
        try {
            List<String> list = JSON.parseArray(ids, String.class);
            Boolean result = sysRoleService.batchRemoveByIds(list);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("sys-role batch delete fail(批量删除失败)--"+ids+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResponse<Boolean> update(@RequestBody SysRoleDO sysRoleDO) {//一定要通过id来修改
        try {
            Boolean result = sysRoleService.updateByObj(sysRoleDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            logger.error("sys-role update fail(更新失败)--"+sysRoleDO.toString()+":{}", e.getMessage());
            return BaseResponse.getRespByResultBool(false);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public BaseResponse<SysRoleDO> get(@RequestBody SysRoleDO sysRoleDO) {//可以通过id来查找，也可以同唯一性的条件来查找出唯一的数据，例如username是不相同，唯一的，就可以定位到唯一的数据
        SysRoleDO result = null;
        try {
            result = (SysRoleDO) sysRoleService.getOneByObj(sysRoleDO);
            return new BaseResponse<SysRoleDO>(result);
        } catch (Exception e) {
            logger.error("sys-role get fail(获取失败)--"+sysRoleDO.toString()+":{}", e.getMessage());
            return new BaseResponse(result);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageInfo<SysRoleDO> getList(@RequestBody SysRoleDO sysRoleDO) {//通过输入page页数和rows每页查询的行数来查询lsit，如果不输入，默认值查询第一页；如果改用select（Obj）方法输入唯一性字段来查询会查到相关唯一的记录。
        try {
            List<SysRoleDO> list = sysRoleService.getListByObj(sysRoleDO);
            return new PageInfo<SysRoleDO>(list);
        } catch (Exception e) {
            logger.error("sys-role get list fail(获取列表失败)--"+sysRoleDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public BaseResponse<List<SysRoleDO>> getAll() {
        try {
            List<SysRoleDO> list = sysRoleService.getALL();
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("sys-role get all(获取所有数据失败)-- :{}", e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Integer getCount(@RequestBody SysRoleDO sysRoleDO) {//输入为null，查询全部的数量，输入唯一性的字段，根据该字段数值查询唯一，数量为1
        try {
            Integer count = sysRoleService.getCount(sysRoleDO);
            return count;
        } catch (Exception e) {
            logger.error("sys-role count fail(统计数目失败)--"+sysRoleDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

    /**
     * 根据输入字段和值，进行模糊查询
     * @param sysRoleDO
     * @return
     * searchKey-查询的字段，searchValue-查询字段的值
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public PageInfo<SysRoleDO> searchList(@RequestBody SysRoleDO sysRoleDO) {
        try {
            List<SysRoleDO> list = sysRoleService.searchListByKV(sysRoleDO);
            return new PageInfo<SysRoleDO>(list);
        } catch (Exception e) {
            logger.error("sys-role search query fail(搜索查询失败)--"+sysRoleDO.toString()+":{}", e.getMessage());
            return null;
        }
    }

}
