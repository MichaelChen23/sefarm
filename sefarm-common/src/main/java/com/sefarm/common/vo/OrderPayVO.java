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
     * 支付时间
     */
    private String payTime;

    /**
     * 支付方式： alipay-支付宝；wechat-微信支付；cash-现金
     */
    private String payType;

    /**
     * 第三方支付，实际支付人账号
     */
    private String payAccount;

    /**
     * 实际支付流水号
     */
    private String payTradeNo;

    /**
     * 第三方支付反馈状态
     */
    private String payStatus;

    /**
     * 支付状态更新时间
     */
    private String updateTime;

    /**
     * 支付结束时间
     */
    private String endTime;

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

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public String getPayTradeNo() {
        return payTradeNo;
    }

    public void setPayTradeNo(String payTradeNo) {
        this.payTradeNo = payTradeNo;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
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

    @Override
    public String toString() {
        return "OrderPayVO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", account='" + account + '\'' +
                ", payAmount=" + payAmount +
                ", payTime='" + payTime + '\'' +
                ", payType='" + payType + '\'' +
                ", payAccount='" + payAccount + '\'' +
                ", payTradeNo='" + payTradeNo + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
