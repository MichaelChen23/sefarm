package com.sefarm.model.order;

import com.sefarm.common.base.BaseDO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * 订单项的实体类
 *
 * @author mc
 * @date 2018-3-24
 */
@Table(name = "sefarm_order_item")
public class OrderItemDO extends BaseDO implements Serializable {
    /**
     * 订单项ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 产品id
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 产品名称
     */
    @Column(name = "product_name")
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
    @Column(name = "carry_fee")
    private BigDecimal carryFee;

    /**
     * 总金额：单价*数量+配送费；默认为0
     */
    private BigDecimal total;

    /**
     * 是否评价过：n-未评价；y-已评价；默认为n 
     */
    @Column(name = "comment_flag")
    private String commentFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取订单项ID
     *
     * @return id - 订单项ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置订单项ID
     *
     * @param id 订单项ID
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
     * 获取产品id
     *
     * @return product_id - 产品id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置产品id
     *
     * @param productId 产品id
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取产品名称
     *
     * @return product_name - 产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名称
     *
     * @param productName 产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取产品单价：默认为0
     *
     * @return price - 产品单价：默认为0
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置产品单价：默认为0
     *
     * @param price 产品单价：默认为0
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取产品数量：默认为0
     *
     * @return number - 产品数量：默认为0
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置产品数量：默认为0
     *
     * @param number 产品数量：默认为0
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取产品单位
     *
     * @return unit - 产品单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置产品单位
     *
     * @param unit 产品单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取配送费：默认为0，0等于免邮
     *
     * @return carry_fee - 配送费：默认为0，0等于免邮
     */
    public BigDecimal getCarryFee() {
        return carryFee;
    }

    /**
     * 设置配送费：默认为0，0等于免邮
     *
     * @param carryFee 配送费：默认为0，0等于免邮
     */
    public void setCarryFee(BigDecimal carryFee) {
        this.carryFee = carryFee;
    }

    /**
     * 获取总金额：单价*数量+配送费；默认为0
     *
     * @return total - 总金额：单价*数量+配送费；默认为0
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * 设置总金额：单价*数量+配送费；默认为0
     *
     * @param total 总金额：单价*数量+配送费；默认为0
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * 获取是否评价过：n-未评价；y-已评价；默认为n 
     *
     * @return comment_flag - 是否评价过：n-未评价；y-已评价；默认为n 
     */
    public String getCommentFlag() {
        return commentFlag;
    }

    /**
     * 设置是否评价过：n-未评价；y-已评价；默认为n 
     *
     * @param commentFlag 是否评价过：n-未评价；y-已评价；默认为n 
     */
    public void setCommentFlag(String commentFlag) {
        this.commentFlag = commentFlag;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新人
     *
     * @return update_by - 更新人
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新人
     *
     * @param updateBy 更新人
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "OrderItemDO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", unit='" + unit + '\'' +
                ", carryFee=" + carryFee +
                ", total=" + total +
                ", commentFlag='" + commentFlag + '\'' +
                ", remark='" + remark + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}