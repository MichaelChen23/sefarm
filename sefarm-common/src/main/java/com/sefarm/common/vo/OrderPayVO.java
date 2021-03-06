package com.sefarm.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 分页显示 订单支付 VO类
 *
 * @author mc
 * @date 2018-4-18
 */
public class OrderPayVO implements Serializable {

    /**
     * 订单支付id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 支付金额：默认为0
     */
    private BigDecimal payAmount;

    /**
     * 创建时间/支付时间
     */
    private String createTime;

    /**
     * 支付方式： wechat-微信支付；alipay-支付宝
     */
    private String payType;

    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 公众号id
     */
    private String appId;

    /**
     * 支付时间戳，秒级
     */
    private String timeStamp;

    /**
     * 随机字符串
     */
    private String nonceStr;

    /**
     * 订单详情扩展字符串，微信JS支付参数名为：package
     */
    private String prepayId;

    /**
     * 签名类型：默认MD5
     */
    private String signType;

    /**
     * 签名，微信统一下单返回参数是sign
     */
    private String paySign;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 设备号
     */
    private String deviceInfo;

    /**
     * 交易类型：JSAPI 公众号支付；NATIVE 扫码支付；APP APP支付
     */
    private String tradeType;

    /**
     * 付款银行
     */
    private String bankType;

    /**
     * 货币种类，默认人民币：CNY
     */
    private String feeType;

    /**
     * 是否关注公众账号：Y-关注，N-未关注
     */
    private String subscribeFlag;

    /**
     * 微信支付订单号
     */
    private String transactionId;

    /**
     * 第三方支付反馈状态：PAY_SUCCESS 支付成功；PAY_FAIL 支付失败；REFUNDIND 退款中；REFUND_SUCCESS 退款成功；REFUND_FAIL 退款失败
     */
    private String payStatus;

    /**
     * 微信统一下单返回错误码
     */
    private String errCode;

    /**
     * 微信统一下单返回错误码描述
     */
    private String errCodeDes;

    /**
     * 支付ip地址
     */
    private String payIp;

    /**
     * 支付状态更新时间
     */
    private String updateTime;

    /**
     * 支付结束时间
     */
    private String endTime;

    /**
     * 退款时间
     */
    private String refundTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getSubscribeFlag() {
        return subscribeFlag;
    }

    public void setSubscribeFlag(String subscribeFlag) {
        this.subscribeFlag = subscribeFlag;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public String getPayIp() {
        return payIp;
    }

    public void setPayIp(String payIp) {
        this.payIp = payIp;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    @Override
    public String toString() {
        return "OrderPayVO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", account='" + account + '\'' +
                ", payAmount=" + payAmount +
                ", createTime='" + createTime + '\'' +
                ", payType='" + payType + '\'' +
                ", openid='" + openid + '\'' +
                ", appId='" + appId + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", prepayId='" + prepayId + '\'' +
                ", signType='" + signType + '\'' +
                ", paySign='" + paySign + '\'' +
                ", mchId='" + mchId + '\'' +
                ", deviceInfo='" + deviceInfo + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", bankType='" + bankType + '\'' +
                ", feeType='" + feeType + '\'' +
                ", subscribeFlag='" + subscribeFlag + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", errCode='" + errCode + '\'' +
                ", errCodeDes='" + errCodeDes + '\'' +
                ", payIp='" + payIp + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", refundTime='" + refundTime + '\'' +
                '}';
    }
}
