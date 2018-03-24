package com.sefarm.model.system;

import com.sefarm.common.base.BaseDO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 系统资源的实体类
 *
 * @author mc
 * @date 2018-3-24
 */
@Table(name = "sefarm_sys_resource")
public class SysResourceDO extends BaseDO implements Serializable {
    /**
     * 系统资源ID 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序号，1最小，默认为1
     */
    private Integer sort;

    /**
     * 是否公共资源：y-是；n-不是，默认n
     */
    private String common;

    /**
     * 类型：m-菜单；b-按钮，默认m
     */
    private String type;

    /**
     * 链接
     */
    private String url;

    /**
     * 父级资源id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 父级资源id集合，用‘,’隔开
     */
    @Column(name = "parent_ids")
    private String parentIds;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态：y-启用；n-禁用，默认为y
     */
    private String status;

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
     * 获取系统资源ID 
     *
     * @return id - 系统资源ID 
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置系统资源ID 
     *
     * @param id 系统资源ID 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取资源名称
     *
     * @return name - 资源名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置资源名称
     *
     * @param name 资源名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
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
     * 获取是否公共资源：y-是；n-不是，默认n
     *
     * @return common - 是否公共资源：y-是；n-不是，默认n
     */
    public String getCommon() {
        return common;
    }

    /**
     * 设置是否公共资源：y-是；n-不是，默认n
     *
     * @param common 是否公共资源：y-是；n-不是，默认n
     */
    public void setCommon(String common) {
        this.common = common;
    }

    /**
     * 获取类型：m-菜单；b-按钮，默认m
     *
     * @return type - 类型：m-菜单；b-按钮，默认m
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型：m-菜单；b-按钮，默认m
     *
     * @param type 类型：m-菜单；b-按钮，默认m
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取链接
     *
     * @return url - 链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置链接
     *
     * @param url 链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取父级资源id
     *
     * @return parent_id - 父级资源id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父级资源id
     *
     * @param parentId 父级资源id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取父级资源id集合，用‘,’隔开
     *
     * @return parent_ids - 父级资源id集合，用‘,’隔开
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * 设置父级资源id集合，用‘,’隔开
     *
     * @param parentIds 父级资源id集合，用‘,’隔开
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
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
        return "SysResourceDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", sort=" + sort +
                ", common='" + common + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", parentId=" + parentId +
                ", parentIds='" + parentIds + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}