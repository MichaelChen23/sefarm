package com.sefarm.model.product;

import com.sefarm.common.base.BaseDO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品的实体类
 *
 * @author mc
 * @date 2018-3-24
 */
@Table(name = "sefarm_product")
public class ProductDO extends BaseDO implements Serializable {
    /**
     * 产品ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
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
    @Column(name = "now_price")
    private BigDecimal nowPrice;

    /**
     * 小图片地址
     */
    private String picture;

    /**
     * 是否新品: y-是；n-否, 默认是n
     */
    @Column(name = "new_flag")
    private String newFlag;

    /**
     * 是否特价: y-是；n-否, 默认是n
     */
    @Column(name = "sale_flag")
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
    @Column(name = "sell_count")
    private Long sellCount;

    /**
     * 剩余库存数：默认为0
     */
    private Long stock;

    /**
     * 评价量：默认为0
     */
    @Column(name = "reply_hit")
    private Long replyHit;

    /**
     * 搜索关键词
     */
    @Column(name = "search_word")
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
    @Column(name = "product_type_id")
    private Long productTypeId;

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
     * 产品详细介绍html页面
     */
    @Column(name = "product_html")
    private String productHtml;

    /**
     * 产品多张图片集合，逗号”,”分割
     */
    private String images;

    /**
     * 产品详情页面描述
     */
    private String description;

    /**
     * 获取产品ID
     *
     * @return id - 产品ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置产品ID
     *
     * @param id 产品ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取产品名
     *
     * @return name - 产品名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置产品名
     *
     * @param name 产品名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取产品简介
     *
     * @return introduce - 产品简介
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置产品简介
     *
     * @param introduce 产品简介
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * 获取定价：默认为0
     *
     * @return price - 定价：默认为0
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置定价：默认为0
     *
     * @param price 定价：默认为0
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取现价：默认为0
     *
     * @return now_price - 现价：默认为0
     */
    public BigDecimal getNowPrice() {
        return nowPrice;
    }

    /**
     * 设置现价：默认为0
     *
     * @param nowPrice 现价：默认为0
     */
    public void setNowPrice(BigDecimal nowPrice) {
        this.nowPrice = nowPrice;
    }

    /**
     * 获取小图片地址
     *
     * @return picture - 小图片地址
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置小图片地址
     *
     * @param picture 小图片地址
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取是否新品: y-是；n-否, 默认是n
     *
     * @return new_flag - 是否新品: y-是；n-否, 默认是n
     */
    public String getNewFlag() {
        return newFlag;
    }

    /**
     * 设置是否新品: y-是；n-否, 默认是n
     *
     * @param newFlag 是否新品: y-是；n-否, 默认是n
     */
    public void setNewFlag(String newFlag) {
        this.newFlag = newFlag;
    }

    /**
     * 获取是否特价: y-是；n-否, 默认是n
     *
     * @return sale_flag - 是否特价: y-是；n-否, 默认是n
     */
    public String getSaleFlag() {
        return saleFlag;
    }

    /**
     * 设置是否特价: y-是；n-否, 默认是n
     *
     * @param saleFlag 是否特价: y-是；n-否, 默认是n
     */
    public void setSaleFlag(String saleFlag) {
        this.saleFlag = saleFlag;
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
     * 获取产品状态: new-新增；on-已上架；off-已下架；del-已删除
     *
     * @return status - 产品状态: new-新增；on-已上架；off-已下架；del-已删除
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置产品状态: new-新增；on-已上架；off-已下架；del-已删除
     *
     * @param status 产品状态: new-新增；on-已上架；off-已下架；del-已删除
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取销售数量：默认为0
     *
     * @return sell_count - 销售数量：默认为0
     */
    public Long getSellCount() {
        return sellCount;
    }

    /**
     * 设置销售数量：默认为0
     *
     * @param sellCount 销售数量：默认为0
     */
    public void setSellCount(Long sellCount) {
        this.sellCount = sellCount;
    }

    /**
     * 获取剩余库存数：默认为0
     *
     * @return stock - 剩余库存数：默认为0
     */
    public Long getStock() {
        return stock;
    }

    /**
     * 设置剩余库存数：默认为0
     *
     * @param stock 剩余库存数：默认为0
     */
    public void setStock(Long stock) {
        this.stock = stock;
    }

    /**
     * 获取评价量：默认为0
     *
     * @return reply_hit - 评价量：默认为0
     */
    public Long getReplyHit() {
        return replyHit;
    }

    /**
     * 设置评价量：默认为0
     *
     * @param replyHit 评价量：默认为0
     */
    public void setReplyHit(Long replyHit) {
        this.replyHit = replyHit;
    }

    /**
     * 获取搜索关键词
     *
     * @return search_word - 搜索关键词
     */
    public String getSearchWord() {
        return searchWord;
    }

    /**
     * 设置搜索关键词
     *
     * @param searchWord 搜索关键词
     */
    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    /**
     * 获取产品详情页面标题
     *
     * @return title - 产品详情页面标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置产品详情页面标题
     *
     * @param title 产品详情页面标题
     */
    public void setTitle(String title) {
        this.title = title;
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
     * 获取产品类型id
     *
     * @return product_type_id - 产品类型id
     */
    public Long getProductTypeId() {
        return productTypeId;
    }

    /**
     * 设置产品类型id
     *
     * @param productTypeId 产品类型id
     */
    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
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

    /**
     * 获取产品详细介绍html页面
     *
     * @return product_html - 产品详细介绍html页面
     */
    public String getProductHtml() {
        return productHtml;
    }

    /**
     * 设置产品详细介绍html页面
     *
     * @param productHtml 产品详细介绍html页面
     */
    public void setProductHtml(String productHtml) {
        this.productHtml = productHtml;
    }

    /**
     * 获取产品多张图片集合，逗号”,”分割
     *
     * @return images - 产品多张图片集合，逗号”,”分割
     */
    public String getImages() {
        return images;
    }

    /**
     * 设置产品多张图片集合，逗号”,”分割
     *
     * @param images 产品多张图片集合，逗号”,”分割
     */
    public void setImages(String images) {
        this.images = images;
    }

    /**
     * 获取产品详情页面描述
     *
     * @return description - 产品详情页面描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置产品详情页面描述
     *
     * @param description 产品详情页面描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductDO{" +
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
                ", searchWord='" + searchWord + '\'' +
                ", title='" + title + '\'' +
                ", remark='" + remark + '\'' +
                ", productTypeId=" + productTypeId +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", productHtml='" + productHtml + '\'' +
                ", images='" + images + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}