package com.sefarm.model.system;

import com.sefarm.common.base.BaseDO;
import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "sefarm_sys_user")
public class SysUserDO extends BaseDO implements Serializable {
    /**
     * 系统用户ID 
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户帐号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * MD5密码盐
     */
    private String salt;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 性别：m-男；w-女
     */
    private String sex;

    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 系统角色id 
     */
    @Column(name = "sys_role_id")
    private Long sysRoleId;

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
     * 获取系统用户ID 
     *
     * @return id - 系统用户ID 
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置系统用户ID 
     *
     * @param id 系统用户ID 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户帐号
     *
     * @return username - 用户帐号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户帐号
     *
     * @param username 用户帐号
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取MD5密码盐
     *
     * @return salt - MD5密码盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置MD5密码盐
     *
     * @param salt MD5密码盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取头像
     *
     * @return avatar - 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像
     *
     * @param avatar 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取真实姓名
     *
     * @return name - 真实姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置真实姓名
     *
     * @param name 真实姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别：m-男；w-女
     *
     * @return sex - 性别：m-男；w-女
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别：m-男；w-女
     *
     * @param sex 性别：m-男；w-女
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取生日
     *
     * @return birthday - 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取电话号码
     *
     * @return phone - 电话号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话号码
     *
     * @param phone 电话号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取手机号码
     *
     * @return mobile - 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
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
        return "SysUserDO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", sysRoleId=" + sysRoleId +
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