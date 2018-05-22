package com.sefarm.controller.common;

import com.github.wxpay.sdk.WXPay;
import com.sefarm.common.constant.tips.SuccessTip;
import com.sefarm.common.util.FileUtil;
import com.sefarm.common.util.HttpKit;
import com.sefarm.config.wechat.SeFarmWXPayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
            data.put("device_info", "WEB");
            data.put("fee_type", "CNY");
            data.put("total_fee", totalFee);
            data.put("spbill_create_ip", payIp);
            data.put("notify_url", "http://www.ji-book.com/api/wechat/notify");
            data.put("trade_type", tradeType);
            data.put("openid", openid);
            data.put("time_start", orderCreateTime);
            logger.info("微信下单前的data: {} " , data.toString());
            SeFarmWXPayConfig seFarmWXPayConfig = new SeFarmWXPayConfig();
            WXPay wxPay = new WXPay(seFarmWXPayConfig);
            result = wxPay.unifiedOrder(data);
            return result;
        } catch (Exception e) {
            logger.error("微信下单失败: {}", e.toString());
            return result;
        }
    }

}
