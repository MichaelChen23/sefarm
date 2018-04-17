package com.sefarm.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 分页显示 订单项 VO类
 *
 * @author mc
 * @date 2018-4-17
 */
public class OrderItemVO implements Serializable {

    /**
     * 订单项ID
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
     * 产品id
     */
    private Long productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品单价：默认为0
     */
    private BigDecimal price;

    /**
     * 产品数量：默认为0
     */
    private Integer number;

    /**
     * 产品单位
     */
    private String unit;

    /**
     * 配送费：默认为0，0等于免邮
     */
    private BigDecimal carryFee;

    /**
     * 总金额：单价*数量+配送费；默认为0
     */
    private BigDecimal total;

    /**
     * 是否评价过：n-未评价；y-已评价；默认为n
     */
    private String commentFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户帐号
     */
    private String account;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private String updateTime;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getCarryFee() {
        return carryFee;
    }

    public void setCarryFee(BigDecimal carryFee) {
        this.carryFee = carryFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCommentFlag() {
        return commentFlag;
    }

    public void setCommentFlag(String commentFlag) {
        this.commentFlag = commentFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "OrderItemVO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", unit='" + unit + '\'' +
                ", carryFee=" + carryFee +
                ", total=" + total +
                ", commentFlag='" + commentFlag + '\'' +
                ", remark='" + remark + '\'' +
                ", account='" + account + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
