package com.sefarm.model.order;

import com.sefarm.common.base.BaseDO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单支付记录的实体类
 *
 * @author mc
 * @date 2018-5-20
 */
@Table(name = "sefarm_order_pay")
public class OrderPayDO extends BaseDO implements Serializable {
    /**
     * 订单支付id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 支付金额：默认为0
     */
    @Column(name = "pay_amount")
    private BigDecimal payAmount;

    /**
     * 创建时间/支付时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 支付方式：wechat-微信支付；alipay-支付宝
     */
    @Column(name = "pay_type")
    private String payType;

    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 公众号id
     */
    @Column(name = "app_id")
    private String appId;

    /**
     * 支付时间戳，秒级
     */
    @Column(name = "time_stamp")
    private String timeStamp;

    /**
     * 随机字符串
     */
    @Column(name = "nonce_str")
    private String nonceStr;

    /**
     * 订单详情扩展字符串，微信JS支付参数名为：package
     */
    @Column(name = "prepay_id")
    private String prepayId;

    /**
     * 签名类型：默认MD5
     */
    @Column(name = "sign_type")
    private String signType;

    /**
     * 签名，微信统一下单返回参数是sign
     */
    @Column(name = "pay_sign")
    private String paySign;

    /**
     * 商户号
     */
    @Column(name = "mch_id")
    private String mchId;

    /**
     * 设备号
     */
    @Column(name = "device_info")
    private String deviceInfo;

    /**
     * 交易类型：JSAPI 公众号支付；NATIVE 扫码支付；APP APP支付
     */
    @Column(name = "trade_type")
    private String tradeType;

    /**
     * 第三方支付反馈状态：PAY_SUCCESS 支付成功；PAY_FAIL 支付失败；REFUNDIND 退款中；REFUND_SUCCESS 退款成功；REFUND_FAIL 退款失败
     */
    @Column(name = "pay_status")
    private String payStatus;

    /**
     * 微信统一下单返回错误码
     */
    @Column(name = "err_code")
    private String errCode;

    /**
     * 微信统一下单返回错误码描述
     */
    @Column(name = "err_code_des")
    private String errCodeDes;

    /**
     * 支付ip地址
     */
    @Column(name = "pay_ip")
    private String payIp;

    /**
     * 支付状态更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 支付结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 退款时间
     */
    @Column(name = "refund_time")
    private Date refundTime;

    /**
     * 获取订单支付id
     *
     * @return id - 订单支付id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置订单支付id
     *
     * @param id 订单支付id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取用户账号
     *
     * @return account - 用户账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置用户账号
     *
     * @param account 用户账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取支付金额：默认为0
     *
     * @return pay_amount - 支付金额：默认为0
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * 设置支付金额：默认为0
     *
     * @param payAmount 支付金额：默认为0
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 获取创建时间/支付时间
     *
     * @return create_time - 创建时间/支付时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间/支付时间
     *
     * @param createTime 创建时间/支付时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取支付方式：wechat-微信支付；alipay-支付宝
     *
     * @return pay_type - 支付方式：wechat-微信支付；alipay-支付宝
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置支付方式：wechat-微信支付；alipay-支付宝
     *
     * @param payType 支付方式：wechat-微信支付；alipay-支付宝
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取用户唯一标识
     *
     * @return openid - 用户唯一标识
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置用户唯一标识
     *
     * @param openid 用户唯一标识
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取公众号id
     *
     * @return app_id - 公众号id
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 设置公众号id
     *
     * @param appId 公众号id
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 获取支付时间戳，秒级
     *
     * @return time_stamp - 支付时间戳，秒级
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * 设置支付时间戳，秒级
     *
     * @param timeStamp 支付时间戳，秒级
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * 获取随机字符串
     *
     * @return nonce_str - 随机字符串
     */
    public String getNonceStr() {
        return nonceStr;
    }

    /**
     * 设置随机字符串
     *
     * @param nonceStr 随机字符串
     */
    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    /**
     * 获取订单详情扩展字符串，微信JS支付参数名为：package
     *
     * @return prepay_id - 订单详情扩展字符串，微信JS支付参数名为：package
     */
    public String getPrepayId() {
        return prepayId;
    }

    /**
     * 设置订单详情扩展字符串，微信JS支付参数名为：package
     *
     * @param prepayId 订单详情扩展字符串，微信JS支付参数名为：package
     */
    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    /**
     * 获取签名类型：默认MD5
     *
     * @return sign_type - 签名类型：默认MD5
     */
    public String getSignType() {
        return signType;
    }

    /**
     * 设置签名类型：默认MD5
     *
     * @param signType 签名类型：默认MD5
     */
    public void setSignType(String signType) {
        this.signType = signType;
    }

    /**
     * 获取签名，微信统一下单返回参数是sign
     *
     * @return pay_sign - 签名，微信统一下单返回参数是sign
     */
    public String getPaySign() {
        return paySign;
    }

    /**
     * 设置签名，微信统一下单返回参数是sign
     *
     * @param paySign 签名，微信统一下单返回参数是sign
     */
    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    /**
     * 获取商户号
     *
     * @return mch_id - 商户号
     */
    public String getMchId() {
        return mchId;
    }

    /**
     * 设置商户号
     *
     * @param mchId 商户号
     */
    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    /**
     * 获取设备号
     *
     * @return device_info - 设备号
     */
    public String getDeviceInfo() {
        return deviceInfo;
    }

    /**
     * 设置设备号
     *
     * @param deviceInfo 设备号
     */
    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    /**
     * 获取交易类型：JSAPI 公众号支付；NATIVE 扫码支付；APP APP支付
     *
     * @return trade_type - 交易类型：JSAPI 公众号支付；NATIVE 扫码支付；APP APP支付
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设置交易类型：JSAPI 公众号支付；NATIVE 扫码支付；APP APP支付
     *
     * @param tradeType 交易类型：JSAPI 公众号支付；NATIVE 扫码支付；APP APP支付
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    /**
     * 获取第三方支付反馈状态：PAY_SUCCESS 支付成功；PAY_FAIL 支付失败；REFUNDIND 退款中；REFUND_SUCCESS 退款成功；REFUND_FAIL 退款失败
     *
     * @return pay_status - 第三方支付反馈状态：PAY_SUCCESS 支付成功；PAY_FAIL 支付失败；REFUNDIND 退款中；REFUND_SUCCESS 退款成功；REFUND_FAIL 退款失败
     */
    public String getPayStatus() {
        return payStatus;
    }

    /**
     * 设置第三方支付反馈状态：PAY_SUCCESS 支付成功；PAY_FAIL 支付失败；REFUNDIND 退款中；REFUND_SUCCESS 退款成功；REFUND_FAIL 退款失败
     *
     * @param payStatus 第三方支付反馈状态：PAY_SUCCESS 支付成功；PAY_FAIL 支付失败；REFUNDIND 退款中；REFUND_SUCCESS 退款成功；REFUND_FAIL 退款失败
     */
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取微信统一下单返回错误码
     *
     * @return err_code - 微信统一下单返回错误码
     */
    public String getErrCode() {
        return errCode;
    }

    /**
     * 设置微信统一下单返回错误码
     *
     * @param errCode 微信统一下单返回错误码
     */
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    /**
     * 获取微信统一下单返回错误码描述
     *
     * @return err_code_des - 微信统一下单返回错误码描述
     */
    public String getErrCodeDes() {
        return errCodeDes;
    }

    /**
     * 设置微信统一下单返回错误码描述
     *
     * @param errCodeDes 微信统一下单返回错误码描述
     */
    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    /**
     * 获取支付ip地址
     *
     * @return pay_ip - 支付ip地址
     */
    public String getPayIp() {
        return payIp;
    }

    /**
     * 设置支付ip地址
     *
     * @param payIp 支付ip地址
     */
    public void setPayIp(String payIp) {
        this.payIp = payIp;
    }

    /**
     * 获取支付状态更新时间
     *
     * @return update_time - 支付状态更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置支付状态更新时间
     *
     * @param updateTime 支付状态更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取支付结束时间
     *
     * @return end_time - 支付结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置支付结束时间
     *
     * @param endTime 支付结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取退款时间
     *
     * @return refund_time - 退款时间
     */
    public Date getRefundTime() {
        return refundTime;
    }

    /**
     * 设置退款时间
     *
     * @param refundTime 退款时间
     */
    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    @Override
    public String toString() {
        return "OrderPayDO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", account='" + account + '\'' +
                ", payAmount=" + payAmount +
                ", createTime=" + createTime +
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
                ", payStatus='" + payStatus + '\'' +
                ", errCode='" + errCode + '\'' +
                ", errCodeDes='" + errCodeDes + '\'' +
                ", payIp='" + payIp + '\'' +
                ", updateTime=" + updateTime +
                ", endTime=" + endTime +
                ", refundTime=" + refundTime +
                '}';
    }
}