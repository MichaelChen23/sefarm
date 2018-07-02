package com.sefarm.controller.user;

import com.github.pagehelper.PageInfo;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.exception.BizExceptionEnum;
import com.sefarm.common.exception.BussinessException;
import com.sefarm.controller.common.BaseController;
import com.sefarm.model.user.UserAddressDO;
import com.sefarm.service.user.IUserAddressService;
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
 * 用户地址的Controller
 *
 * @author mc
 * @date 2018-3-24
 */
@Controller
@RequestMapping("/api")
public class UserAddressController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserAddressController.class);

    private static String PREFIX = "/user/address/";

    @Autowired
    IUserAddressService userAddressService;

    /**
     * 跳转到查看 用户地址列表的页面
     */
    @RequestMapping("/user-adr")
    public String index() {
        return PREFIX + "address.html";
    }

    /**
     * 跳转到新增 用户地址列表的页面
     */
    @RequestMapping("/user-adr/address_save")
    public String saveView() {
        return PREFIX + "address_save.html";
    }

    /**
     * 跳转到修改 用户地址列表的页面
     */
    @RequestMapping("/user-adr/address_update/{addressId}")
    public String updateView(@PathVariable Long addressId, Model model) {
        if(ToolUtil.isEmpty(addressId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        UserAddressDO query = new UserAddressDO();
        query.setId(addressId);
        UserAddressDO userAddressDO = userAddressService.getOneByObj(query);
        model.addAttribute(userAddressDO);
        return PREFIX + "address_update.html";
    }

    /**
     * 按照查询条件查询 用户地址 列表
     * @return
     */
    @RequestMapping(value = "/user-adr/address_list", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<UserAddressDO> getUserAddressDOPageList(@RequestParam Integer pageIndex, @RequestParam Integer pageSize, @RequestParam(required = false) String sortStr, @RequestParam(required = false) String orderStr,
                                                            @RequestParam(required = false) String account, @RequestParam(required = false) String createTimeBegin, @RequestParam(required = false) String createTimeEnd) {
        try {
            PageInfo<UserAddressDO> result = userAddressService.getUserAddressDOPageList(pageIndex, pageSize, sortStr, orderStr, account, createTimeBegin, createTimeEnd);
            return result;
        } catch (Exception e) {
            logger.error("get user-adr page list fail(获取 用户地址 分页 列表失败) -- :{}", e.getMessage());
            return null;
        }
    }

    /**
     * 添加新增 用户地址
     * @param userAddressDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/user-adr/saveAddress", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse saveAddress(@Valid UserAddressDO userAddressDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            Boolean res = userAddressService.saveByObj(userAddressDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "user-adr save fail(保存失败)--"+userAddressDO.toString()+":{}", true);
        }
    }

    /**
     * 更新 用户地址
     * @param userAddressDO
     * @param result
     * @return
     */
    @RequestMapping(value = "/user-adr/updateAddress", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse updateAddress(@Valid UserAddressDO userAddressDO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            Boolean res = userAddressService.updateByObj(userAddressDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "user-adr update fail(更新失败)--"+userAddressDO.toString()+":{}", true);
        }
    }

    /**
     * 删除 用户地址
     * @param addressId
     * @return
     */
    @RequestMapping(value = "/user-adr/removeAddress", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse removeAddress(@RequestParam Long addressId) {
        if (ToolUtil.isEmpty(addressId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            UserAddressDO userAddressDO = new UserAddressDO();
            userAddressDO.setId(addressId);
            Boolean res = userAddressService.removeByObj(userAddressDO);
            return BaseResponse.getRespByResultBool(res);
        } catch (Exception e) {
            return handleException(e, "user-adr delete fail(删除失败)-- id:"+addressId+":{}", true);
        }
    }


    /**
     * 移动前端——新增保存用户地址
     * @param account
     * @param name
     * @param province
     * @param city
     * @param area
     * @param address
     * @param zip
     * @param phone
     * @param mobile
     * @param defaultFlag
     * @return
     */
    @RequestMapping(value = "/wechat/user-adr/save", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Boolean> save(@RequestParam String account, @RequestParam String name, @RequestParam(required = false) String provinceId, @RequestParam(required = false) String province, @RequestParam(required = false) String cityId, @RequestParam(required = false) String city, @RequestParam(required = false) String areaId, @RequestParam(required = false) String area,
                                      @RequestParam String address, @RequestParam(required = false) String zip, @RequestParam(required = false) String phone, @RequestParam String mobile, @RequestParam(required = false) String defaultFlag) {
        UserAddressDO userAddressDO = new UserAddressDO();
        try {
            //检测accessToken是否失效
            BaseResponse<Boolean> checkToken = checkAccessToken();
            if (!checkToken.getResult()) {
                return checkToken;
            }

            userAddressDO.setAccount(account);
            userAddressDO.setName(name);
            userAddressDO.setProvinceId(provinceId);
            userAddressDO.setProvince(province);
            userAddressDO.setCityId(cityId);
            userAddressDO.setCity(city);
            userAddressDO.setAreaId(areaId);
            userAddressDO.setArea(area);
            userAddressDO.setAddress(address);
            userAddressDO.setZip(zip);
            userAddressDO.setPhone(phone);
            userAddressDO.setMobile(mobile);
            userAddressDO.setDefaultFlag(defaultFlag);
            if (Constant.STATUS_UNLOCK.equals(defaultFlag)) {
                Boolean updateFlag = userAddressService.updateAllDefaultFlag(account);
                if (!updateFlag) {
                    return BaseResponse.getRespByResultBool(false);
                }
            }
            userAddressDO.setCreateTime(new Date());
            Boolean result = userAddressService.saveByObj(userAddressDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            return handleException(e, "user-adr save fail(保存失败)--"+userAddressDO.toString()+":{}", false);
        }
    }

    /**
     * 移动前端——删除用户地址
     * @param userAddressId
     * @return
     */
    @RequestMapping(value = "/wechat/user-adr/remove", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Boolean> remove(@RequestParam Long userAddressId) {//可通过id来删除，可通过其他条件是唯一性的来定位数据来删除，例如username是不相同，唯一的，就可以定位到唯一的数据
        try {
            //检测accessToken是否失效
            BaseResponse<Boolean> checkToken = checkAccessToken();
            if (!checkToken.getResult()) {
                return checkToken;
            }

            UserAddressDO userAddressDO = new UserAddressDO();
            userAddressDO.setId(userAddressId);
            Boolean result = userAddressService.removeByObj(userAddressDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            return handleException(e, "user-adr delete fail(删除失败)-- id:"+userAddressId+":{}", false);
        }
    }

    /**
     * 移动前端——更新编辑用户地址
     * @param id
     * @param account
     * @param name
     * @param province
     * @param city
     * @param area
     * @param address
     * @param zip
     * @param phone
     * @param mobile
     * @param defaultFlag
     * @return
     */
    @RequestMapping(value = "/wechat/user-adr/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<Boolean> update(@RequestParam Long id, @RequestParam String account, @RequestParam String name, @RequestParam(required = false) String provinceId, @RequestParam(required = false) String province, @RequestParam(required = false) String cityId, @RequestParam(required = false) String city, @RequestParam(required = false) String areaId, @RequestParam(required = false) String area,
                                        @RequestParam String address, @RequestParam(required = false) String zip, @RequestParam(required = false) String phone, @RequestParam String mobile, @RequestParam(required = false) String defaultFlag) {//一定要通过id来修改
        UserAddressDO userAddressDO = new UserAddressDO();
        try {
            //检测accessToken是否失效
            BaseResponse<Boolean> checkToken = checkAccessToken();
            if (!checkToken.getResult()) {
                return checkToken;
            }

            userAddressDO.setId(id);
            userAddressDO.setAccount(account);
            userAddressDO.setName(name);
            userAddressDO.setProvinceId(provinceId);
            userAddressDO.setProvince(province);
            userAddressDO.setCityId(cityId);
            userAddressDO.setCity(city);
            userAddressDO.setAreaId(areaId);
            userAddressDO.setArea(area);
            userAddressDO.setAddress(address);
            userAddressDO.setZip(zip);
            userAddressDO.setPhone(phone);
            userAddressDO.setMobile(mobile);
            userAddressDO.setDefaultFlag(defaultFlag);
            if (defaultFlag.equals(Constant.STATUS_UNLOCK)) {
                Boolean updateFlag = userAddressService.updateOtherDefaultFlagById(id, account);
                if (!updateFlag) {
                    return BaseResponse.getRespByResultBool(false);
                }
            }
            userAddressDO.setUpdateBy(account);
            userAddressDO.setUpdateTime(new Date());
            Boolean result = userAddressService.updateByObj(userAddressDO);
            return BaseResponse.getRespByResultBool(result);
        } catch (Exception e) {
            return handleException(e, "user-adr update fail(更新失败)--"+userAddressDO.toString()+":{}", false);
        }
    }

    /**
     * 移动前端——根据用户帐号和是否默认地址标签 获取所有的用户地址
     * @param account
     * @param defaultFlag
     * @return
     */
    @RequestMapping(value = "/wechat/user-adr/getAllListByAccountAndFlag", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<List<UserAddressDO>> getUserAddressDOAllListByAccountAndFlag(@RequestParam String account, @RequestParam(required = false) String defaultFlag) {
        try {
            //检测accessToken是否失效
            BaseResponse<Boolean> checkToken = checkAccessToken();
            if (!checkToken.getResult()) {
                return new BaseResponse<>(checkToken.getCode(), checkToken.getMsg(), null);
            }

            List<UserAddressDO> list = userAddressService.getUserAddressDOAllListByAccountAndFlag(account, defaultFlag);
            return new BaseResponse<>(list);
        } catch (Exception e) {
            return handleException(e, "user-adr get all by account & defaultFlag (根据用户帐号和是否默认地址标签 获取所有的用户地址 数据失败)-- :{}", false);
        }
    }


}
