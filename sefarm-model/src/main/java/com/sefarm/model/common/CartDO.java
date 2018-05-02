package com.sefarm.model.common;

import com.sefarm.common.base.BaseDO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 购物车的实体类
 *
 * @author mc
 * @date 2018-5-1
 */
@Table(name = "sefarm_cart")
public class CartDO extends BaseDO implements Serializable {
    /**
     * 购物车ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 产品ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 产品数量，默认为0
     */
    private Integer number;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取购物车ID
     *
     * @return id - 购物车ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置购物车ID
     *
     * @param id 购物车ID
     */
    public void setId(Long id) {
        this.id = id;
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
     * 获取产品ID
     *
     * @return product_id - 产品ID
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置产品ID
     *
     * @param productId 产品ID
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取产品数量，默认为0
     *
     * @return number - 产品数量，默认为0
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置产品数量，默认为0
     *
     * @param number 产品数量，默认为0
     */
    public void setNumber(Integer number) {
        this.number = number;
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
        return "CartDO{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", productId=" + productId +
                ", number=" + number +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}