package com.sefarm.model.system;

import com.sefarm.common.base.BaseDO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 系统部门的实体类
 *
 * @author mc
 * @date 2018-3-24
 */
@Table(name = "sefarm_sys_dept")
public class SysDeptDO extends BaseDO implements Serializable {
    /**
     * 系统部门ID 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 部门名简称
     */
    private String name;

    /**
     * 部门名全称
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 排序号，1最小，默认为1
     */
    private Integer sort;

    /**
     * 描述
     */
    private String description;

    /**
     * 父部门id
     */
    private Long pid;

    /**
     * 所有父部门id集合，用‘,’隔开
     */
    private String pids;

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
     * 获取系统部门ID 
     *
     * @return id - 系统部门ID 
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置系统部门ID 
     *
     * @param id 系统部门ID 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取部门名简称
     *
     * @return name - 部门名简称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置部门名简称
     *
     * @param name 部门名简称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取部门名全称
     *
     * @return full_name - 部门名全称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置部门名全称
     *
     * @param fullName 部门名全称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
     * 获取父部门id
     *
     * @return pid - 父部门id
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置父部门id
     *
     * @param pid 父部门id
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取所有父部门id集合，用‘,’隔开
     *
     * @return pids - 所有父部门id集合，用‘,’隔开
     */
    public String getPids() {
        return pids;
    }

    /**
     * 设置所有父部门id集合，用‘,’隔开
     *
     * @param pids 所有父部门id集合，用‘,’隔开
     */
    public void setPids(String pids) {
        this.pids = pids;
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
        return "SysDeptDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", sort=" + sort +
                ", description='" + description + '\'' +
                ", pid=" + pid +
                ", pids='" + pids + '\'' +
                ", status='" + status + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}