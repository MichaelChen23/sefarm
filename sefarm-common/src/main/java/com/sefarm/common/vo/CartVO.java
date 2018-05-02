package com.sefarm.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 全部显示 购物车产品 VO类
 *
 * @author mc
 * @date 2018-5-1
 */
public class CartVO implements Serializable {

    /**
     * 购物车ID
     */
    private Long id;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 产品名
     */
    private String productName;

    /**
     * 产品图片
     */
    private String productImage;

    /**
     * 现价
     */
    private BigDecimal nowPrice;

    /**
     * 产品数量，默认为0
     */
    private Integer number;

    /**
     * 创建时间
     */
    private String createTime;

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public BigDecimal getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(BigDecimal nowPrice) {
        this.nowPrice = nowPrice;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CartVO{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", nowPrice=" + nowPrice +
                ", number=" + number +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
