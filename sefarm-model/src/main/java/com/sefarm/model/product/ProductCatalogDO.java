package com.sefarm.model.product;

import com.sefarm.common.base.BaseDO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 产品目录的实体类
 *
 * @author mc
 * @date 2018-3-24
 */
@Table(name = "sefarm_product_catalog")
public class ProductCatalogDO extends BaseDO implements Serializable {
    /**
     * 产品目录ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 产品目录名
     */
    private String name;

    /**
     * 排序号，1最小，默认为1
     */
    private Integer sort;

    /**
     * 状态：y-启用；n-禁用，默认为y
     */
    private String status;

    /**
     * 详述
     */
    private String detail;

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
     * 获取产品目录ID
     *
     * @return id - 产品目录ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置产品目录ID
     *
     * @param id 产品目录ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取产品目录名
     *
     * @return name - 产品目录名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置产品目录名
     *
     * @param name 产品目录名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取排序号，1最小，默认为1
     *
     * @return sort - 排序号，1最小，默认为1
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序号，1最小，默认为1
     *
     * @param sort 排序号，1最小，默认为1
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取状态：y-启用；n-禁用，默认为y
     *
     * @return status - 状态：y-启用；n-禁用，默认为y
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：y-启用；n-禁用，默认为y
     *
     * @param status 状态：y-启用；n-禁用，默认为y
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取详述
     *
     * @return detail - 详述
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置详述
     *
     * @param detail 详述
     */
    public void setDetail(String detail) {
        this.detail = detail;
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
        return "ProductCatalogDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                ", status='" + status + '\'' +
                ", detail='" + detail + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}