package com.sefarm.common.vo;

import java.io.Serializable;

/**
 * 分页显示 产品类型 VO类
 *
 * @author mc
 * @date 2018-4-16
 */
public class ProductTypeVO implements Serializable {

    /**
     * 产品类型ID
     */
    private Long id;

    /**
     * 产品类型名
     */
    private String name;

    /**
     * 产品目录id
     */
    private Long productCatalogId;

    /**
     * 产品目录名
     */
    private String productCatalogName;

    /**
     * 排序号，1最小，默认为1
     */
    private Integer sort;

    /**
     * 状态：y-启用；n-禁用，默认为y
     */
    private String status;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建人
     */
    private String createBy;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductCatalogId() {
        return productCatalogId;
    }

    public void setProductCatalogId(Long productCatalogId) {
        this.productCatalogId = productCatalogId;
    }

    public String getProductCatalogName() {
        return productCatalogName;
    }

    public void setProductCatalogName(String productCatalogName) {
        this.productCatalogName = productCatalogName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
        return "ProductTypeVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productCatalogId=" + productCatalogId +
                ", productCatalogName='" + productCatalogName + '\'' +
                ", sort=" + sort +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
