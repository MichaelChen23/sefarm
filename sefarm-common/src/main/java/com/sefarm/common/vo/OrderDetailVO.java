package com.sefarm.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单详情 VO类
 *
 * @author mc
 * @date 2018-5-7
 */
public class OrderDetailVO implements Serializable {

    /**
     * 订单ID
     */
    private Long id;

    /**
     * 订单编号,唯一
     */
    private String orderNo;

    /**
     * 购买产品的用户账号
     */
    private String account;

    /**
     * 付款方式：alipay-支付宝；wechat-微信支付；cash-现金
     */
    private String payType;

    /**
     * 运送方式：express-邮递； EMS-邮政
     */
    private String carry;

    /**
     * 运送总费用：默认为0-包邮
     */
    private BigDecimal carryFeeTotal;

    /**
     * 产品总数量：默认为0
     */
    private Integer quantity;

    /**
     * 产品总金额：默认为0
     */
    private BigDecimal productTotal;

    /**
     * 订单总金额：产品总金额+运送总费用，默认为0
     */
    private BigDecimal amount;

    /**
     * 订单总兑换积分：默认为0
     */
    private BigDecimal exchangeScore;

    /**
     * 客户的附加要求备注
     */
    private String requirement;

    /**
     * 订单状态：done-完成，pay-支付成功，sending-配送中，undone-未完成，默认undone
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 收货人名字
     */
    private String receiver;

    /**
     * 收货人地址
     */
    private String address;

    /**
     * 收货人电话
     */
    private String mobile;

    /**
     * 配送状态：new-新增；check-审核中；ready-待发货；delivery-已发货；receive-已签收；cancel-已取消；默认：new-新增
     */
    private String deliveryStatus;

    /**
     * 发货时间
     */
    private Date deliveryTime;

    /**
     * 收货时间
     */
    private Date receiveTime;

    /**
     * 订单项list
     */
    private List<OrderItemDetailVO> orderItemDetailVOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCarry() {
        return carry;
    }

    public void setCarry(String carry) {
        this.carry = carry;
    }

    public BigDecimal getCarryFeeTotal() {
        return carryFeeTotal;
    }

    public void setCarryFeeTotal(BigDecimal carryFeeTotal) {
        this.carryFeeTotal = carryFeeTotal;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(BigDecimal productTotal) {
        this.productTotal = productTotal;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getExchangeScore() {
        return exchangeScore;
    }

    public void setExchangeScore(BigDecimal exchangeScore) {
        this.exchangeScore = exchangeScore;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public List<OrderItemDetailVO> getOrderItemDetailVOList() {
        return orderItemDetailVOList;
    }

    public void setOrderItemDetailVOList(List<OrderItemDetailVO> orderItemDetailVOList) {
        this.orderItemDetailVOList = orderItemDetailVOList;
    }

    @Override
    public String toString() {
        return "OrderDetailVO{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", account='" + account + '\'' +
                ", payType='" + payType + '\'' +
                ", carry='" + carry + '\'' +
                ", carryFeeTotal=" + carryFeeTotal +
                ", quantity=" + quantity +
                ", productTotal=" + productTotal +
                ", amount=" + amount +
                ", exchangeScore=" + exchangeScore +
                ", requirement='" + requirement + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", receiver='" + receiver + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                ", deliveryTime=" + deliveryTime +
                ", receiveTime=" + receiveTime +
                ", orderItemDetailVOList=" + orderItemDetailVOList.toArray().toString() +
                '}';
    }
}
