package com.sefarm.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.sefarm.common.Constant;
import com.sefarm.common.base.BaseResponse;
import com.sefarm.common.constant.tips.SuccessTip;
import com.sefarm.common.exception.BaseExcepitonEnum;
import com.sefarm.common.util.FileUtil;
import com.sefarm.common.util.HttpKit;
import com.sefarm.config.wechat.SeFarmWXPayConfig;
import com.sefarm.model.user.UserDO;
import com.sefarm.util.ToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.github.wxpay.sdk.WXPayUtil.MD5;


/**
 * controller基础类，控制层常量和统一方法
 *
 * @author mc
 * @date 2018-3-30
 */
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected static String SUCCESS = "SUCCESS";
    protected static String ERROR = "ERROR";

    protected static String REDIRECT = "redirect:";
    protected static String FORWARD = "forward:";

    protected static SuccessTip SUCCESS_TIP = new SuccessTip();

    /**
     * 重复数据key
     */
    private static final String DUPLICATE = "Duplicate";

    /**
     * 数据为空key
     */
    private static final String NULLDATA = "Null";


    protected HttpServletRequest getHttpServletRequest() {
        return HttpKit.getRequest();
    }

    protected HttpServletResponse getHttpServletResponse() {
        return HttpKit.getResponse();
    }

    protected HttpSession getSession() {
        return HttpKit.getRequest().getSession();
    }

    protected HttpSession getSession(Boolean flag) {
        return HttpKit.getRequest().getSession(flag);
    }

    protected String getPara(String name) {
        return HttpKit.getRequest().getParameter(name);
    }

    protected void setAttr(String name, Object value) {
        HttpKit.getRequest().setAttribute(name, value);
    }

    /**
     * 获取请求头信息，用于微信接口获取token等请求头信息
     * add by mc 2018-6-22
     * @param headName
     * @return
     */
    protected String getHeader(String headName) {
        return HttpKit.getRequest().getHeader(headName);
    }

    /**
     * 获取当前操作系统用户的帐号
     * add by mc 2018-7-2
     * @return
     */
    protected String getCurrentSysUser() {
        if (getSession() != null) {
            return (String) getSession().getAttribute("username");
        } else {
            return "";
        }
    }

    protected Integer getSystemInvokCount() {
        return (Integer) this.getHttpServletRequest().getServletContext().getAttribute("systemCount");
    }

    /**
     * 把service层的分页信息，封装为bootstrap table通用的分页封装
     */
//    protected <T> PageInfoBT<T> packForBT(Page<T> page) {
//        return new PageInfoBT<T>(page);
//    }

    /**
     * 包装一个list，让list增加额外属性
     */
    protected Object warpObject(BaseControllerWarpper warpper) {
        return warpper.warp();
    }

    /**
     * 删除cookie
     */
    protected void deleteCookieByName(String cookieName) {
        Cookie[] cookies = this.getHttpServletRequest().getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                Cookie temp = new Cookie(cookie.getName(), "");
                temp.setMaxAge(0);
                this.getHttpServletResponse().addCookie(temp);
            }
        }
    }

    /**
     * 返回前台文件流
     *
     * @author mc
     * @date 2018-3-30
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, String filePath) {
        byte[] bytes = FileUtil.toByteArray(filePath);
        return renderFile(fileName, bytes);
    }

    /**
     * 返回前台文件流
     *
     * @author mc
     * @date 2018-3-30
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, byte[] fileBytes) {
        String dfileName = null;
        try {
            dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.CREATED);
    }

    /**
     * 统一处理异常
     * @param e
     * @param logMsg
     * @param isExceptionDetail
     * @return
     */
    @ResponseBody
    public BaseResponse handleException(Exception e, String logMsg, boolean isExceptionDetail) {
        logger.error(logMsg, e.getMessage());
        if (e instanceof RuntimeException) {
            //输出到后台管理系统，返回详细异常信息
            if (isExceptionDetail) {
                return new BaseResponse(400, e.getMessage());
            }
            if (e.getMessage().indexOf(DUPLICATE) != -1) {
                //重复插入主键相同的数据
                return  BaseResponse.getResultByException(BaseExcepitonEnum.EXIST_DATA);
            } else if (e.getMessage().indexOf(NULLDATA) != -1) {
                //空指针异常
                return  BaseResponse.getResultByException(BaseExcepitonEnum.NULL_DATA);
            }
            //运行异常
            return BaseResponse.getResultByException(BaseExcepitonEnum.RUNTIME_ERROR);
        }
        //输出到后台管理系统，返回详细异常信息
        if (isExceptionDetail) {
            return new BaseResponse(400, e.getMessage());
        }
        return BaseResponse.getResultByException(BaseExcepitonEnum.SYSTEM_ERROR);
    }

    /**
     * 微信下单
     * add by mc 2018-5-20
     * @param orderNo 订单号
     * @param totalFee 支付金额，分
     * @param tradeType 交易方式 默认JSAPI
     * @param openid 用户唯一标识码
     * @param orderCreateTime 订单生成时间 格式为yyyyMMddHHmmss
     * @param payIp ip地址
     * @return
     */
    public Map<String, String> doUnifiedOrder(String orderNo, String totalFee, String tradeType, String openid, String orderCreateTime, String payIp) {
        Map<String, String> result = new HashMap<>(10);
        HashMap<String, String> data = new HashMap<>(10);
        try {
            data.put("body", "广州农夫诚品商贸有限公司-网购SeFarm富硒农产品");
            data.put("out_trade_no",  orderNo);
            data.put("fee_type", "CNY");
            data.put("total_fee", totalFee);
            data.put("device_info", "WEB");
            data.put("spbill_create_ip", payIp);
            data.put("time_start", orderCreateTime);
            data.put("notify_url", "http://www.ji-book.com/api/wechat/notify");
            data.put("trade_type", tradeType);
            data.put("openid", openid);
            logger.info("微信下单前的data: {} " , data.toString());
            SeFarmWXPayConfig seFarmWXPayConfig = new SeFarmWXPayConfig();
            WXPay wxPay = new WXPay(seFarmWXPayConfig, WXPayConstants.SignType.MD5);
            result = wxPay.unifiedOrder(data);
            return result;
        } catch (Exception e) {
            logger.error("微信下单失败: {}", e.toString());
            return result;
        }
    }

    /**
     * 生成32位随机数
     * add by mc 2018-5-23
     * @return
     */
    public String getNonceStr() {
//        String res = String.valueOf(System.currentTimeMillis());
        String res = WXPayUtil.generateNonceStr();
        return res;
    }

    /**
     * 根据prepay_id和传给前端支付的参数生成签名
     * add by mc 2018-5-23
     * @param appId
     * @param timeStamp
     * @param nonceStr
     * @param prepayId
     * @param signType
     * @return
     * @throws Exception
     */
    public String getSign(String appId, String timeStamp, String nonceStr, String prepayId, String signType) throws Exception {
        StringBuilder signSb = new StringBuilder();
        signSb.append("appId="+appId+"&");
        signSb.append("nonceStr="+nonceStr+"&");
        signSb.append("package=prepay_id="+prepayId+"&");
        signSb.append("signType="+signType+"&");
        signSb.append("timeStamp="+timeStamp+"&");
        signSb.append("key="+Constant.WECHAT_API_KEY);
        String sign = MD5(signSb.toString()).toUpperCase();
        return sign;
    }

    /**
     * 检查access_token是否失效过期，如果过期了，前端直接去重新登录就好，不用掉微信的刷新token接口
     * add by mc 2018-6-26
     * @return
     */
    public BaseResponse<Boolean> checkAccessToken() {
        BaseResponse<Boolean> result = new BaseResponse<>(Boolean.TRUE);
        //用请求头获取
        String accessToken = getHeader("accessToken").trim();
        UserDO userInfo = (UserDO) getSession().getAttribute(accessToken);
        //session没有保存该用户信息
        if (userInfo == null || (userInfo != null && StringUtils.isBlank(userInfo.getOpenid()))) {
            result.setCode(Constant.FAIL_CODE);
            result.setMsg("登录失败，请重新登录！");
            result.setResult(Boolean.FALSE);
            return result;
        }
        String openId = userInfo.getOpenid();
        Map<String, Object> queryMaps = new HashMap<>(2);
        queryMaps.put("access_token", accessToken);
        queryMaps.put("openid", openId);
        try {
            JSONObject resultJson = HttpKit.doGet(Constant.WECHAT_CHECK_ACCESS_TOKEN_URL, queryMaps);
            Integer errcode = resultJson.getInteger("errcode");
            String errmsg = resultJson.getString("errmsg");
            if (!ToolUtil.isEmpty(errcode) && StringUtils.isNotBlank(errmsg)) {
                //accessToken过期
                if (Constant.ACCESS_TOKEN_OVERTIME_CODE.equals(errcode)) {
                    //微信返回的errcode为40003，errmsg为invalid openid
                    result.setCode(errcode);
                    result.setMsg("accessToken已失效");
                    result.setResult(Boolean.FALSE);
                }
            }
            return result;
        } catch (Exception e) {
            logger.error("check accessToken fail(检查accessToken失败) -- accessToken:" + accessToken + " openId:" + openId + " :{}", e.getMessage());
            return new BaseResponse<>(Boolean.FALSE);
        }
    }

}
