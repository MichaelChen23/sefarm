package com.sefarm.controller.system;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.system.AdvertDO;
import com.sefarm.service.system.IAdvertService;
import com.sefarm.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 广告的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@Controller
@RequestMapping("/api/advert")
public class AdvertController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdvertController.class);

    private static String PREFIX = "/system/advert/";

    @Autowired
    public IAdvertService advertService;

    /**
     * 跳转到查看 广告列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "advert.html";
    }

    /**
     * 跳转到新增 广告的页面
     */
    @RequestMapping("/advert_save")
    public String saveView() {
        return PREFIX + "advert_save.html";
    }

    /**
     * 跳转到修改 广告的页面
     */
    @RequestMapping("/advert_update/{advertId}")
    public String updateView(@PathVariable Long advertId, Model model) {
        if(ToolUtil.isEmpty(advertId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        AdvertDO query = new AdvertDO();
        query.setId(advertId);
        AdvertDO advertDO = advertService.getOneByObj(query);
        model.addAttribute(advertDO);
        return PREFIX + "advert_update.html";
    }

    /**
     * 按照查询条件查询 广告列表
     * @return
     */
    @RequestMapping(value = "/advert_list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<AdvertDO> getAdvertDOList(@RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sortStr, @RequestParam(required = false) String orderStr,
                                              @RequestParam(required = false) String name, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd) {
        try {
            PageInfo<AdvertDO> result = advertService.getAdvertDOList(pageIndex, pageSize, sortStr, orderStr, name, createTimeBegin, createTimeEnd);
            return result;
        } catch (Exception e) {
            logger.error("get advert list fail(获取 广告列表失败) -- :{}", e.getMessage());
            return null;
        }
    }

    /**
     * 新增 广告
     * @param advertDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse save(@Valid AdvertDO advertDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            // 完善广告信息
            advertDO.setCreateBy("sys");
            advertDO.setCreateTime(new Date());
            Boolean res = advertService.saveByObj(advertDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return baseException.handleException(e, logger, "advert save fail(保存失败)--"+advertDO.toString()+":{}", true);
        }
    }

    /**
     * 更新编辑 广告
     * @param advertDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@Valid AdvertDO advertDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            advertDO.setUpdateBy("sys");
            advertDO.setUpdateTime(new Date());
            Boolean res = advertService.updateByObj(advertDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return baseException.handleException(e, logger, "advert update fail(更新失败)--"+advertDO.toString()+":{}", true);
        }
    }

    /**
     * 删除 广告
     * @param advertId
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse remove(@RequestParam Long advertId) {//可通过id来删除，可通过其他条件是唯一性的来定位数据来删除，例如username是不相同，唯一的，就可以定位到唯一的数据
        if (ToolUtil.isEmpty(advertId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            AdvertDO advertDO = new AdvertDO();
            advertDO.setId(advertId);
            Boolean res = advertService.removeByObj(advertDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return baseException.handleException(e, logger, "advert delete fail(删除失败)-- id:"+advertId+":{}", true);
        }
    }

    /**
     * 移动前端 分页查询
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getPageList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<AdvertDO>> getAdvertPageList(@RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize) {
        try {
            List<AdvertDO> list = advertService.getAdvertPageList(pageIndex, pageSize);
            return new BaseResponse<>(list);
        } catch (Exception e) {
            logger.error("advert get page list (获取广告分页list失败)-- :{}", e.getMessage());
            return baseException.handleException(e, logger, "advert get page list (获取广告分页list失败)-- pageIndex:"+pageIndex+"-- pageSize:"+pageSize+":{}", false);
        }
    }

}
