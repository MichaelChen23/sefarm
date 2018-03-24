package com.sefarm.model.user;

import com.sefarm.common.base.BaseDO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * 用户的实体类
 *
 * @author mc
 * @date 2018-3-24
 */
@Table(name = "sefarm_user")
public class UserDO extends BaseDO implements Serializable {
    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码：6-18位数字与字母的结合
     */
    private String password;

    /**
     * 账号类型：默认为手机号用户-mobile
     */
    @Column(name = "account_type")
    private String accountType;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户等级，默认R1
     */
    private String rank;

    /**
     * 钱包余额: （单位：元）默认0
     */
    private BigDecimal amount;

    /**
     * 用户积分：默认为0
     */
    private BigDecimal score;

    /**
     * 账号是否激活：y-已激活；n-未激活，默认为n 
     */
    @Column(name = "active_flag")
    private String activeFlag;

    /**
     * 账号是否锁住：y-已锁；n-未锁，默认为n
     */
    @Column(name = "lock_flag")
    private String lockFlag;

    /**
     * 账号锁住时间
     */
    @Column(name = "lock_start_time")
    private Date lockStartTime;

    /**
     * 账号解锁时间
     */
    @Column(name = "lock_end_time")
    private Date lockEndTime;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 账号注册时间/创建时间
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
     * 短信验证代码
     */
    @Column(name = "sms_code")
    private String smsCode;

    /**
     * 发送短信用途
     */
    @Column(name = "sms_useful")
    private String smsUseful;

    /**
     * 发送短信代码时间
     */
    @Column(name = "send_code_time")
    private Date sendCodeTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取用户ID
     *
     * @return id - 用户ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户ID
     *
     * @param id 用户ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取账号
     *
     * @return account - 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号
     *
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account;
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
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取密码：6-18位数字与字母的结合
     *
     * @return password - 密码：6-18位数字与字母的结合
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码：6-18位数字与字母的结合
     *
     * @param password 密码：6-18位数字与字母的结合
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取账号类型：默认为手机号用户-mobile
     *
     * @return account_type - 账号类型：默认为手机号用户-mobile
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * 设置账号类型：默认为手机号用户-mobile
     *
     * @param accountType 账号类型：默认为手机号用户-mobile
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
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
     * 获取用户等级，默认R1
     *
     * @return rank - 用户等级，默认R1
     */
    public String getRank() {
        return rank;
    }

    /**
     * 设置用户等级，默认R1
     *
     * @param rank 用户等级，默认R1
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * 获取钱包余额: （单位：元）默认0
     *
     * @return amount - 钱包余额: （单位：元）默认0
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置钱包余额: （单位：元）默认0
     *
     * @param amount 钱包余额: （单位：元）默认0
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取用户积分：默认为0
     *
     * @return score - 用户积分：默认为0
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * 设置用户积分：默认为0
     *
     * @param score 用户积分：默认为0
     */
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    /**
     * 获取账号是否激活：y-已激活；n-未激活，默认为n 
     *
     * @return active_flag - 账号是否激活：y-已激活；n-未激活，默认为n 
     */
    public String getActiveFlag() {
        return activeFlag;
    }

    /**
     * 设置账号是否激活：y-已激活；n-未激活，默认为n 
     *
     * @param activeFlag 账号是否激活：y-已激活；n-未激活，默认为n 
     */
    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }

    /**
     * 获取账号是否锁住：y-已锁；n-未锁，默认为n
     *
     * @return lock_flag - 账号是否锁住：y-已锁；n-未锁，默认为n
     */
    public String getLockFlag() {
        return lockFlag;
    }

    /**
     * 设置账号是否锁住：y-已锁；n-未锁，默认为n
     *
     * @param lockFlag 账号是否锁住：y-已锁；n-未锁，默认为n
     */
    public void setLockFlag(String lockFlag) {
        this.lockFlag = lockFlag;
    }

    /**
     * 获取账号锁住时间
     *
     * @return lock_start_time - 账号锁住时间
     */
    public Date getLockStartTime() {
        return lockStartTime;
    }

    /**
     * 设置账号锁住时间
     *
     * @param lockStartTime 账号锁住时间
     */
    public void setLockStartTime(Date lockStartTime) {
        this.lockStartTime = lockStartTime;
    }

    /**
     * 获取账号解锁时间
     *
     * @return lock_end_time - 账号解锁时间
     */
    public Date getLockEndTime() {
        return lockEndTime;
    }

    /**
     * 设置账号解锁时间
     *
     * @param lockEndTime 账号解锁时间
     */
    public void setLockEndTime(Date lockEndTime) {
        this.lockEndTime = lockEndTime;
    }

    /**
     * 获取最后登录时间
     *
     * @return last_login_time - 最后登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastLoginTime 最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取账号注册时间/创建时间
     *
     * @return create_time - 账号注册时间/创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置账号注册时间/创建时间
     *
     * @param createTime 账号注册时间/创建时间
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
     * 获取短信验证代码
     *
     * @return sms_code - 短信验证代码
     */
    public String getSmsCode() {
        return smsCode;
    }

    /**
     * 设置短信验证代码
     *
     * @param smsCode 短信验证代码
     */
    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    /**
     * 获取发送短信用途
     *
     * @return sms_useful - 发送短信用途
     */
    public String getSmsUseful() {
        return smsUseful;
    }

    /**
     * 设置发送短信用途
     *
     * @param smsUseful 发送短信用途
     */
    public void setSmsUseful(String smsUseful) {
        this.smsUseful = smsUseful;
    }

    /**
     * 获取发送短信代码时间
     *
     * @return send_code_time - 发送短信代码时间
     */
    public Date getSendCodeTime() {
        return sendCodeTime;
    }

    /**
     * 设置发送短信代码时间
     *
     * @param sendCodeTime 发送短信代码时间
     */
    public void setSendCodeTime(Date sendCodeTime) {
        this.sendCodeTime = sendCodeTime;
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
        return "UserDO{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", accountType='" + accountType + '\'' +
                ", email='" + email + '\'' +
                ", rank='" + rank + '\'' +
                ", amount=" + amount +
                ", score=" + score +
                ", activeFlag='" + activeFlag + '\'' +
                ", lockFlag='" + lockFlag + '\'' +
                ", lockStartTime=" + lockStartTime +
                ", lockEndTime=" + lockEndTime +
                ", lastLoginTime=" + lastLoginTime +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", smsCode='" + smsCode + '\'' +
                ", smsUseful='" + smsUseful + '\'' +
                ", sendCodeTime=" + sendCodeTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}