package com.sefarm.model.user;

import com.sefarm.common.base.BaseDO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户的实体类
 *
 * @author mc
 * @date 2018-5-15
 */
@Table(name = "sefarm_user")
public class UserDO extends BaseDO implements Serializable {
    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户的唯一标识
     */
    private String openid;

    /**
     * 性别，值为1时是男性，值为2时是女性，值为0时是未知，默认0
     */
    private Integer sex;

    /**
     * 用户头像
     */
    private String headimgurl;

    /**
     * 国家，如中国为CN
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 语言：zh_CN中文
     */
    private String language;

    /**
     * 微信网页授权接口调用凭证
     */
    @Column(name = "access_token")
    private String accessToken;

    /**
     * 账号类型：微信用户-wechat，手机号注册用户-mobile，默认：wechat
     */
    @Column(name = "account_type")
    private String accountType;

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
     * 账号是否锁住：y-已锁；n-未锁，默认为n
     */
    @Column(name = "lock_flag")
    private String lockFlag;

    /**
     * 账号注册时间/创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取用户昵称
     *
     * @return nickname - 用户昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置用户昵称
     *
     * @param nickname 用户昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取用户的唯一标识
     *
     * @return openid - 用户的唯一标识
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置用户的唯一标识
     *
     * @param openid 用户的唯一标识
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取性别，值为1时是男性，值为2时是女性，值为0时是未知，默认0
     *
     * @return sex - 性别，值为1时是男性，值为2时是女性，值为0时是未知，默认0
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别，值为1时是男性，值为2时是女性，值为0时是未知，默认0
     *
     * @param sex 性别，值为1时是男性，值为2时是女性，值为0时是未知，默认0
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取用户头像
     *
     * @return headimgurl - 用户头像
     */
    public String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * 设置用户头像
     *
     * @param headimgurl 用户头像
     */
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    /**
     * 获取国家，如中国为CN
     *
     * @return country - 国家，如中国为CN
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家，如中国为CN
     *
     * @param country 国家，如中国为CN
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取省份
     *
     * @return province - 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取语言：zh_CN中文
     *
     * @return language - 语言：zh_CN中文
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置语言：zh_CN中文
     *
     * @param language 语言：zh_CN中文
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 获取微信网页授权接口调用凭证
     *
     * @return access_token - 微信网页授权接口调用凭证
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 设置微信网页授权接口调用凭证
     *
     * @param accessToken 微信网页授权接口调用凭证
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * 获取账号类型：微信用户-wechat，手机号注册用户-mobile，默认：wechat
     *
     * @return account_type - 账号类型：微信用户-wechat，手机号注册用户-mobile，默认：wechat
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * 设置账号类型：微信用户-wechat，手机号注册用户-mobile，默认：wechat
     *
     * @param accountType 账号类型：微信用户-wechat，手机号注册用户-mobile，默认：wechat
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
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
        return "UserDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", nickname='" + nickname + '\'' +
                ", openid='" + openid + '\'' +
                ", sex=" + sex +
                ", headimgurl='" + headimgurl + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", language='" + language + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", accountType='" + accountType + '\'' +
                ", rank='" + rank + '\'' +
                ", amount=" + amount +
                ", score=" + score +
                ", lockFlag='" + lockFlag + '\'' +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}