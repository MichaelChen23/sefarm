package com.sefarm.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单项详情 VO类
 *
 * @author mc
 * @date 2018-5-7
 */
public class OrderItemDetailVO implements Serializable {

    /**
     * 订单项ID
     */
    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品图片
     */
    private String productImage;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
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

    @Override
    public String toString() {
        return "OrderItemDetailVO{" +
                "id=" + id +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", unit='" + unit + '\'' +
                ", carryFee=" + carryFee +
                ", total=" + total +
                ", commentFlag='" + commentFlag + '\'' +
                '}';
    }
}
