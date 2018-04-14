package com.sefarm.model.order;

import com.sefarm.common.base.BaseDO;

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
 * @date 2018-3-24
 */
@Table(name = "sefarm_order_pay")
public class OrderPayDO extends BaseDO implements Serializable {
    /**
     * 创建人
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
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 支付方式： alipay-支付宝；wechat-微信支付；cash-现金
     */
    @Column(name = "pay_type")
    private String payType;

    /**
     * 第三方支付，实际支付人账号
     */
    @Column(name = "pay_account")
    private String payAccount;

    /**
     * 实际支付流水号
     */
    @Column(name = "pay_trade_no")
    private String payTradeNo;

    /**
     * 第三方支付反馈状态
     */
    @Column(name = "pay_status")
    private String payStatus;

    /**
     * 获取创建人
     *
     * @return id - 创建人
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置创建人
     *
     * @param id 创建人
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
     * 获取支付时间
     *
     * @return pay_time - 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置支付时间
     *
     * @param payTime 支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取支付方式： alipay-支付宝；wechat-微信支付；cash-现金
     *
     * @return pay_type - 支付方式： alipay-支付宝；wechat-微信支付；cash-现金
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置支付方式： alipay-支付宝；wechat-微信支付；cash-现金
     *
     * @param payType 支付方式： alipay-支付宝；wechat-微信支付；cash-现金
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取第三方支付，实际支付人账号
     *
     * @return pay_account - 第三方支付，实际支付人账号
     */
    public String getPayAccount() {
        return payAccount;
    }

    /**
     * 设置第三方支付，实际支付人账号
     *
     * @param payAccount 第三方支付，实际支付人账号
     */
    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    /**
     * 获取实际支付流水号
     *
     * @return pay_trade_no - 实际支付流水号
     */
    public String getPayTradeNo() {
        return payTradeNo;
    }

    /**
     * 设置实际支付流水号
     *
     * @param payTradeNo 实际支付流水号
     */
    public void setPayTradeNo(String payTradeNo) {
        this.payTradeNo = payTradeNo;
    }

    /**
     * 获取第三方支付反馈状态
     *
     * @return pay_status - 第三方支付反馈状态
     */
    public String getPayStatus() {
        return payStatus;
    }

    /**
     * 设置第三方支付反馈状态
     *
     * @param payStatus 第三方支付反馈状态
     */
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    @Override
    public String toString() {
        return "OrderPayDO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", account='" + account + '\'' +
                ", payAmount=" + payAmount +
                ", payTime=" + payTime +
                ", payType='" + payType + '\'' +
                ", payAccount='" + payAccount + '\'' +
                ", payTradeNo='" + payTradeNo + '\'' +
                ", payStatus='" + payStatus + '\'' +
                '}';
    }
}