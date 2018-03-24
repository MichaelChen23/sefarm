package com.sefarm.model.system;

import com.sefarm.common.base.BaseDO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 广告的实体类
 *
 * @author mc
 * @date 2018-3-24
 */
@Table(name = "sefarm_advert")
public class AdvertDO extends BaseDO implements Serializable {
    /**
     * 首页广告ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 广告标题
     */
    private String title;

    /**
     * 广告图片
     */
    private String image;

    /**
     * 广告内容
     */
    private String content;

    /**
     * 跳转的链接
     */
    private String url;

    /**
     * 状态：y-启用；n-禁用，默认为y 
     */
    private String status;

    /**
     * 广告上架时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 广告下架时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 排序号，1最小，默认为1
     */
    private Integer sort;

    /**
     * 广告类别：home-首页，默认为home
     */
    private String type;

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
     * 广告html内容
     */
    private String html;

    /**
     * 获取首页广告ID
     *
     * @return id - 首页广告ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置首页广告ID
     *
     * @param id 首页广告ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取广告标题
     *
     * @return title - 广告标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置广告标题
     *
     * @param title 广告标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取广告图片
     *
     * @return image - 广告图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置广告图片
     *
     * @param image 广告图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取广告内容
     *
     * @return content - 广告内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置广告内容
     *
     * @param content 广告内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取跳转的链接
     *
     * @return url - 跳转的链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置跳转的链接
     *
     * @param url 跳转的链接
     */
    public void setUrl(String url) {
        this.url = url;
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
     * 获取广告上架时间
     *
     * @return start_time - 广告上架时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置广告上架时间
     *
     * @param startTime 广告上架时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取广告下架时间
     *
     * @return end_time - 广告下架时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置广告下架时间
     *
     * @param endTime 广告下架时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
     * 获取广告类别：home-首页，默认为home
     *
     * @return type - 广告类别：home-首页，默认为home
     */
    public String getType() {
        return type;
    }

    /**
     * 设置广告类别：home-首页，默认为home
     *
     * @param type 广告类别：home-首页，默认为home
     */
    public void setType(String type) {
        this.type = type;
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

    /**
     * 获取广告html内容
     *
     * @return html - 广告html内容
     */
    public String getHtml() {
        return html;
    }

    /**
     * 设置广告html内容
     *
     * @param html 广告html内容
     */
    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public String toString() {
        return "AdvertDO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", status='" + status + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", sort=" + sort +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", html='" + html + '\'' +
                '}';
    }
}