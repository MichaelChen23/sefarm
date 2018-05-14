package com.sefarm.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.util.HttpKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public BaseResponse<JSONObject> getUserInfoByAccessTokenAndOpenId(@RequestParam String accessToken, @RequestParam String openId) {
        Map<String, Object> queryMaps = new HashMap<>(3);
        queryMaps.put("access_token", accessToken);
        queryMaps.put("openid", openId);
        queryMaps.put("lang", Constant.WECHAT_LANG_TYPE);
        try {
            JSONObject result = HttpKit.doGet(Constant.WECHAT_GET_USER_INFO_URL, queryMaps);
            return new BaseResponse<>(result);
        } catch (Exception e) {
            logger.error("get userinfo by accessToken and openId fail(获取用户信息失败) -- accessToken:" + accessToken + " openId:" + openId + " :{}", e.getMessage());
            return new BaseResponse<>(null);
        }
    }
}
