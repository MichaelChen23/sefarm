package com.sefarm.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 分页显示 产品 VO类
 *
 * @author mc
 * @date 2018-4-16
 */
public class ProductVO implements Serializable {

    /**
     * 产品ID
     */
    private Long id;

    /**
     * 产品名
     */
    private String name;

    /**
     * 产品简介
     */
    private String introduce;

    /**
     * 定价：默认为0
     */
    private BigDecimal price;

    /**
     * 现价：默认为0
     */
    private BigDecimal nowPrice;

    /**
     * 小图片地址
     */
    private String picture;

    /**
     * 是否新品: y-是；n-否, 默认是n
     */
    private String newFlag;

    /**
     * 是否特价: y-是；n-否, 默认是n
     */
    private String saleFlag;

    /**
     * 产品单位
     */
    private String unit;

    /**
     * 排序号，1最小，默认为1
     */
    private Integer sort;

    /**
     * 产品状态: new-新增；on-已上架；off-已下架；del-已删除
     */
    private String status;

    /**
     * 销售数量：默认为0
     */
    private Long sellCount;

    /**
     * 剩余库存数：默认为0
     */
    private Long stock;

    /**
     * 评价量：默认为0
     */
    private Long replyHit;

    /**
     * 好评量：默认为0
     */
    private Long goodHit;

    /**
     * 搜索关键词
     */
    private String searchWord;

    /**
     * 产品详情页面标题
     */
    private String title;

    /**
     * 备注
     */
    private String remark;

    /**
     * 产品类型id
     */
    private Long productTypeId;

    /**
     * 产品类型名
     */
    private String productTypeName;

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

    /**
     * 产品详细介绍html页面
     */
    private String productHtml;

    /**
     * 产品多张图片集合，逗号”,”分割
     */
    private String images;

    /**
     * 产品详情页面描述
     */
    private String description;

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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(BigDecimal nowPrice) {
        this.nowPrice = nowPrice;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNewFlag() {
        return newFlag;
    }

    public void setNewFlag(String newFlag) {
        this.newFlag = newFlag;
    }

    public String getSaleFlag() {
        return saleFlag;
    }

    public void setSaleFlag(String saleFlag) {
        this.saleFlag = saleFlag;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public Long getSellCount() {
        return sellCount;
    }

    public void setSellCount(Long sellCount) {
        this.sellCount = sellCount;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getReplyHit() {
        return replyHit;
    }

    public void setReplyHit(Long replyHit) {
        this.replyHit = replyHit;
    }

    public Long getGoodHit() {
        return goodHit;
    }

    public void setGoodHit(Long goodHit) {
        this.goodHit = goodHit;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
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

    public String getProductHtml() {
        return productHtml;
    }

    public void setProductHtml(String productHtml) {
        this.productHtml = productHtml;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", price=" + price +
                ", nowPrice=" + nowPrice +
                ", picture='" + picture + '\'' +
                ", newFlag='" + newFlag + '\'' +
                ", saleFlag='" + saleFlag + '\'' +
                ", unit='" + unit + '\'' +
                ", sort=" + sort +
                ", status='" + status + '\'' +
                ", sellCount=" + sellCount +
                ", stock=" + stock +
                ", replyHit=" + replyHit +
                ", goodHit=" + goodHit +
                ", searchWord='" + searchWord + '\'' +
                ", title='" + title + '\'' +
                ", remark='" + remark + '\'' +
                ", productTypeId=" + productTypeId +
                ", productTypeName='" + productTypeName + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", productHtml='" + productHtml + '\'' +
                ", images='" + images + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
