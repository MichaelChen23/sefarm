package com.sefarm.controller.user;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.user.UserRankDO;
import com.sefarm.service.user.IUserRankService;
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
 * 用户等级的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@Controller
@RequestMapping("/api/user-rank")
public class UserRankController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserRankController.class);

    private static String PREFIX = "/user/rank/";

    @Autowired
    public IUserRankService userRankService;

    /**
     * 跳转到查看 用户等级列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "rank.html";
    }

    /**
     * 跳转到新增 用户等级列表的页面
     */
    @RequestMapping("/rank_save")
    public String saveView() {
        return PREFIX + "rank_save.html";
    }

    /**
     * 跳转到修改 用户等级列表的页面
     */
    @RequestMapping("/rank_update/{rankId}")
    public String updateView(@PathVariable Long rankId, Model model) {
        if(ToolUtil.isEmpty(rankId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        UserRankDO query = new UserRankDO();
        query.setId(rankId);
        UserRankDO userRankDO = userRankService.getOneByObj(query);
        model.addAttribute(userRankDO);
        return PREFIX + "rank_update.html";
    }

    /**
     * 按照查询条件查询 用户等级 列表
     * @return
     */
    @RequestMapping(value = "/rank_list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<UserRankDO> getUserRankDOPageList(@RequestParam Integer pageIndex, @RequestParam Integer pageSize, @RequestParam(required = false) String sortStr, @RequestParam(required = false) String orderStr,
                                                      @RequestParam(required = false) String name, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd) {
        try {
            PageInfo<UserRankDO> result = userRankService.getUserRankDOPageList(pageIndex, pageSize, sortStr, orderStr, name, createTimeBegin, createTimeEnd);
            return result;
        } catch (Exception e) {
            logger.error("get user-rank page list fail(获取 用户等级 分页 列表失败) -- :{}", e.getMessage());
            return null;
        }
    }

    /**
     * 添加新增 用户等级
     * @param userRankDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/saveRank", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse saveRank(@Valid UserRankDO userRankDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            Boolean res = userRankService.saveByObj(userRankDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "user-rank save fail(保存失败)--"+userRankDO.toString()+":{}", true);
        }
    }

    /**
     * 更新 用户等级
     * @param userRankDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/updateRank", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse updateRank(@Valid UserRankDO userRankDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            Boolean res = userRankService.updateByObj(userRankDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "user-rank update fail(更新失败)--"+userRankDO.toString()+":{}", true);
        }
    }

    /**
     * 删除 用户等级
     * @param rankId
     * @return
     */
    @RequestMapping(value = "/removeRank", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse removeRank(@RequestParam Long rankId) {
        if (ToolUtil.isEmpty(rankId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            UserRankDO userRankDO = new UserRankDO();
            userRankDO.setId(rankId);
            Boolean res = userRankService.removeByObj(userRankDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "user-rank delete fail(删除失败)-- id:"+rankId+":{}", true);
        }
    }

}
