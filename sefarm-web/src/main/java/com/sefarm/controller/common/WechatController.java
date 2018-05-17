package com.sefarm.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.util.HttpKit;
import com.sefarm.model.user.UserDO;
import com.sefarm.service.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信登录 验证等 控制器
 *
 * @author mc
 * @date 2018-5-14
 */
@Controller
@RequestMapping("/api/wechat")
public class WechatController {

    private static final Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    public IUserService userService;

    /**
     * 根据code获取accessToken
     * @param code
     * @return
     */
    @RequestMapping(value = "/getAccessTokenByCode", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<JSONObject> getAccessTokenByCode(@RequestParam String code) {
        Map<String, Object> queryMaps = new HashMap<>(4);
        queryMaps.put("appid", Constant.WECHAT_APPID);
        queryMaps.put("secret", Constant.WECHAT_APPSECRET);
        queryMaps.put("code", code);
        queryMaps.put("grant_type", Constant.WECHAT_GRANT_TYPE);
        try {
            JSONObject result = HttpKit.doGet(Constant.WECHAT_GET_ACCESS_TOKEN_URL, queryMaps);
            return new BaseResponse<>(result);
        } catch (Exception e) {
            logger.error("get accessToken by code fail(获取微信accessToken失败) -- code:"+code+" :{}", e.getMessage());
            return new BaseResponse<>(null);
        }
    }

    /**
     * 根据accessToken和openId获取userInfo用户信息
     * @param accessToken
     * @param openId
     * @return
     */
    @RequestMapping(value = "/getUserInfoByAccessTokenAndOpenId", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<UserDO> getUserInfoByAccessTokenAndOpenId(@RequestParam String accessToken, @RequestParam String openId) {
        Map<String, Object> queryMaps = new HashMap<>(3);
        queryMaps.put("access_token", accessToken);
        queryMaps.put("openid", openId);
        queryMaps.put("lang", Constant.WECHAT_LANG_TYPE);
        try {
            JSONObject resultJson = HttpKit.doGet(Constant.WECHAT_GET_USER_INFO_URL, queryMaps);
            //先根据openid查询有没有该用户，如果没该用户就保存，如果有就更新该用户
            String openid = resultJson.getString("openid");
            String nickname = resultJson.getString("nickname");
            String headimgurl = resultJson.getString("headimgurl");
            Integer sex = resultJson.getInteger("sex");
            String country = resultJson.getString("country");
            String province = resultJson.getString("province");
            String city = resultJson.getString("city");
            String language = resultJson.getString("language");
            Boolean result = false;
            UserDO user = new UserDO();
            user.setOpenid(openid);
            UserDO userDO = userService.getOneByObj(user);
            if (userDO != null && userDO.getId() != 0) {
                if (Constant.STATUS_LOCK.equals(userDO.getStatus())) {
                    //如果用户被停用，不能获取该用户信息
                    return new BaseResponse<>(null);
                }
                UserDO updateUserDO = userDO;
                updateUserDO.setNickname(nickname);
                updateUserDO.setHeadimgurl(headimgurl);
                updateUserDO.setSex(sex);
                updateUserDO.setCountry(country);
                updateUserDO.setProvince(province);
                updateUserDO.setCity(city);
                updateUserDO.setLanguage(language);
                updateUserDO.setAccessToken(accessToken);
                updateUserDO.setLastLoginTime(new Date());
                result = userService.updateByObj(updateUserDO);
                return new BaseResponse<>(result? updateUserDO : userDO);
            } else {
                user.setNickname(nickname);
                user.setHeadimgurl(headimgurl);
                user.setSex(sex);
                user.setCountry(country);
                user.setProvince(province);
                user.setCity(city);
                user.setLanguage(language);
                user.setAccessToken(accessToken);
                user.setCreateTime(new Date());
                UserDO newUserDO = userService.saveWechatUser(user);
                return new BaseResponse<>(newUserDO);
            }
        } catch (Exception e) {
            logger.error("get userinfo by accessToken and openId fail(获取用户信息失败) -- accessToken:" + accessToken + " openId:" + openId + " :{}", e.getMessage());
            return new BaseResponse<>(null);
        }
    }
}
