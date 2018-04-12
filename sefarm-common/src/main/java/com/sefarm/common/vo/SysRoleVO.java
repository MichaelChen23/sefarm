package com.sefarm.common.vo;

import java.io.Serializable;

/**
 * 分页显示系统角色VO类
 *
 * @author mc
 * @date 2018-4-11
 */
public class SysRoleVO implements Serializable {

    /**
     * 系统角色ID
     */
    private Long id;

    /**
     * 系统角色名
     */
    private String name;

    /**
     * 系统角色代码
     */
    private String code;

    /**
     * 排序号，1最小，默认为1
     */
    private Integer sort;

    /**
     * 上级角色id
     */
    private Long pid;

    /**
     * 上级角色名
     */
    private String pName;

    /**
     * 所在部门id
     */
    private Long sysDeptId;

    /**
     * 所在部门
     */
    private String deptName;

    /**
     * 状态
     */
    private String status;

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
     * 备注
     */
    private String remark;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Long getSysDeptId() {
        return sysDeptId;
    }

    public void setSysDeptId(Long sysDeptId) {
        this.sysDeptId = sysDeptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SysRoleVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", sort=" + sort +
                ", pid=" + pid +
                ", pName='" + pName + '\'' +
                ", sysDeptId=" + sysDeptId +
                ", deptName='" + deptName + '\'' +
                ", status='" + status + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
