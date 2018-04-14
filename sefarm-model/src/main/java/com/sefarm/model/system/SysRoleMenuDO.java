package com.sefarm.model.system;

import com.sefarm.common.base.BaseDO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色菜单关联的实体类
 *
 * @author mc
 * @date 2018-3-24
 */
@Table(name = "sefarm_sys_role_menu")
public class SysRoleMenuDO extends BaseDO implements Serializable {
    /**
     * 系统角色菜单ID 
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 系统角色id 
     */
    @Column(name = "sys_role_id")
    private Long sysRoleId;

    /**
     * 系统菜单id
     */
    @Column(name = "sys_menu_id")
    private Long sysMenuId;

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
     * 获取系统角色菜单ID 
     *
     * @return id - 系统角色菜单ID 
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置系统角色菜单ID 
     *
     * @param id 系统角色菜单ID 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取系统角色id 
     *
     * @return sys_role_id - 系统角色id 
     */
    public Long getSysRoleId() {
        return sysRoleId;
    }

    /**
     * 设置系统角色id 
     *
     * @param sysRoleId 系统角色id 
     */
    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    /**
     * 获取系统菜单id
     *
     * @return sys_menu_id - 系统菜单id
     */
    public Long getSysMenuId() {
        return sysMenuId;
    }

    /**
     * 设置系统菜单id
     *
     * @param sysMenuId 系统菜单id
     */
    public void setSysMenuId(Long sysMenuId) {
        this.sysMenuId = sysMenuId;
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
        return "SysRoleMenuDO{" +
                "id=" + id +
                ", sysRoleId=" + sysRoleId +
                ", sysMenuId=" + sysMenuId +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}