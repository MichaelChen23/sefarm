package com.sefarm.model.system;

import com.sefarm.common.base.BaseDO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色的实体类
 *
 * @author mc
 * @date 2018-3-24
 */
@Table(name = "sefarm_sys_role")
public class SysRoleDO extends BaseDO implements Serializable {
    /**
     * 系统角色ID 
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编号
     */
    private String code;

    /**
     * 排序号，1最小，默认为1
     */
    private Integer sort;

    /**
     * 父角色id
     */
    private Long pid;

    /**
     * 系统公司部门id
     */
    @Column(name = "sys_dept_id")
    private Long sysDeptId;

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
     * 备注
     */
    private String remark;

    /**
     * 获取系统角色ID 
     *
     * @return id - 系统角色ID 
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置系统角色ID 
     *
     * @param id 系统角色ID 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取角色名称
     *
     * @return name - 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     *
     * @param name 角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取角色编号
     *
     * @return code - 角色编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置角色编号
     *
     * @param code 角色编号
     */
    public void setCode(String code) {
        this.code = code;
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
     * 获取父角色id
     *
     * @return pid - 父角色id
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置父角色id
     *
     * @param pid 父角色id
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取系统公司部门id
     *
     * @return sys_dept_id - 系统公司部门id
     */
    public Long getSysDeptId() {
        return sysDeptId;
    }

    /**
     * 设置系统公司部门id
     *
     * @param sysDeptId 系统公司部门id
     */
    public void setSysDeptId(Long sysDeptId) {
        this.sysDeptId = sysDeptId;
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

    @Override
    public String toString() {
        return "SysRoleDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", sort=" + sort +
                ", pid=" + pid +
                ", sysDeptId=" + sysDeptId +
                ", status='" + status + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}