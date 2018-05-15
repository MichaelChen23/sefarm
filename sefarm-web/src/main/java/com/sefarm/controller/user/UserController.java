package com.sefarm.controller.user;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.constant.tips.ErrorTip;
import com.sefarm.common.constant.tips.Tip;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.user.UserDO;
import com.sefarm.service.user.IUserService;
import com.sefarm.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@Controller
@RequestMapping("/api/user")
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static String PREFIX = "/user/base/";

    @Autowired
    public IUserService userService;

    /**
     * 跳转到查看 用户列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "user.html";
    }

    /**
     * 跳转到新增 用户列表的页面
     */
    @RequestMapping("/user_save")
    public String saveView() {
        return PREFIX + "user_save.html";
    }

    /**
     * 跳转到修改 用户列表的页面
     */
    @RequestMapping("/user_update/{userId}")
    public String updateView(@PathVariable Long userId, Model model) {
        if(ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        UserDO query = new UserDO();
        query.setId(userId);
        UserDO userDO = userService.getOneByObj(query);
        model.addAttribute(userDO);
        return PREFIX + "user_update.html";
    }

    /**
     * 按照查询条件查询 用户 列表
     * @return
     */
    @RequestMapping(value = "/user_list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<UserDO> getUserDOPageList(@RequestParam Integer pageIndex, @RequestParam Integer pageSize, @RequestParam String sortStr, @RequestParam String orderStr, @RequestParam(required = false) String name, @RequestParam(required = false) String mobile, @RequestParam(required = false) String address,
                                              @RequestParam(required = false) Integer sexInt, @RequestParam(required = false) String lockStr, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd, @RequestParam(required = false) String lastLoginTimeBegin, @RequestParam(required = false) String lastLoginTimeEnd) {
        try {
            PageInfo<UserDO> result = userService.getUserDOPageList(pageIndex, pageSize, sortStr, orderStr, name, mobile, address, sexInt, lockStr, createTimeBegin, createTimeEnd, lastLoginTimeBegin, lastLoginTimeEnd);
            return result;
        } catch (Exception e) {
            logger.error("get user page list fail(获取 用户 分页 列表失败) -- :{}", e.getMessage());
            return null;
        }
    }

    /**
     * 添加新增 用户
     * @param userDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    @ResponseBody
    public Tip saveUser(@Valid UserDO userDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            Boolean res = userService.saveByObj(userDO);
            if (res) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("user save fail(保存失败)--"+userDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 更新 用户
     * @param userDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public Tip updateUser(@Valid UserDO userDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            if (userDO != null) {
                Boolean res = userService.updateByObj(userDO);
                if (res) {
                    return SUCCESS_TIP;
                }
            }
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        } catch (Exception e) {
            logger.error("user update fail(更新失败)--"+userDO.toString()+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

    /**
     * 删除 用户
     * @param userId
     * @return
     */
    @RequestMapping(value = "/removeUser", method = RequestMethod.POST)
    @ResponseBody
    public Tip removeUser(@RequestParam Long userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            UserDO userDO = new UserDO();
            userDO.setId(userId);
            Boolean result = userService.removeByObj(userDO);
            if (result) {
                return SUCCESS_TIP;
            } else {
                return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
            }
        } catch (Exception e) {
            logger.error("user delete fail(删除失败)--"+userId+":{}", e.getMessage());
            return new ErrorTip(Constant.FAIL_CODE, Constant.FAIL_MSG);
        }
    }

}
