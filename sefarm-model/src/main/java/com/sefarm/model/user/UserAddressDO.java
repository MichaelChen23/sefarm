package com.sefarm.model.user;

import com.sefarm.common.base.BaseDO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户地址的实体类
 *
 * @author mc
 * @date 2018-3-24
 */
@Table(name = "sefarm_user_address")
public class UserAddressDO extends BaseDO implements Serializable {
    /**
     * 用户地址ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 省份id
     */
    @Column(name = "province_id")
    private String provinceId;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市id
     */
    @Column(name = "city_id")
    private String cityId;

    /**
     * 城市
     */
    private String city;

    /**
     * 区域id
     */
    @Column(name = "area_id")
    private String areaId;

    /**
     * 区域
     */
    private String area;

    /**
     * 收货人详细地址
     */
    private String address;

    /**
     * 邮编
     */
    private String zip;

    /**
     * 收货人电话/座机号码
     */
    private String phone;

    /**
     * 收货人手机号码
     */
    private String mobile;

    /**
     * 是否默认地址：y-是；n-不是，
默认为n，第一次填写地址为y 
     */
    @Column(name = "default_flag")
    private String defaultFlag;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取用户地址ID
     *
     * @return id - 用户地址ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户地址ID
     *
     * @param id 用户地址ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户账号
     *
     * @return account - 用户账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置用户账号
     *
     * @param account 用户账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取收货人姓名
     *
     * @return name - 收货人姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置收货人姓名
     *
     * @param name 收货人姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取省份id
     *
     * @return province_id - 省份id
     */
    public String getProvinceId() {
        return provinceId;
    }

    /**
     * 设置省份id
     *
     * @param provinceId 省份id
     */
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
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
     * 获取城市id
     *
     * @return city_id - 城市id
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * 设置城市id
     *
     * @param cityId 城市id
     */
    public void setCityId(String cityId) {
        this.cityId = cityId;
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
     * 获取区域id
     *
     * @return area_id - 区域id
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * 设置区域id
     *
     * @param areaId 区域id
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取区域
     *
     * @return area - 区域
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置区域
     *
     * @param area 区域
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取收货人详细地址
     *
     * @return address - 收货人详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置收货人详细地址
     *
     * @param address 收货人详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取邮编
     *
     * @return zip - 邮编
     */
    public String getZip() {
        return zip;
    }

    /**
     * 设置邮编
     *
     * @param zip 邮编
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * 获取收货人电话/座机号码
     *
     * @return phone - 收货人电话/座机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置收货人电话/座机号码
     *
     * @param phone 收货人电话/座机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取收货人手机号码
     *
     * @return mobile - 收货人手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置收货人手机号码
     *
     * @param mobile 收货人手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取是否默认地址：y-是；n-不是，
默认为n，第一次填写地址为y 
     *
     * @return default_flag - 是否默认地址：y-是；n-不是，
默认为n，第一次填写地址为y 
     */
    public String getDefaultFlag() {
        return defaultFlag;
    }

    /**
     * 设置是否默认地址：y-是；n-不是，
默认为n，第一次填写地址为y 
     *
     * @param defaultFlag 是否默认地址：y-是；n-不是，
默认为n，第一次填写地址为y 
     */
    public void setDefaultFlag(String defaultFlag) {
        this.defaultFlag = defaultFlag;
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
        return "UserAddressDO{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", provinceId='" + provinceId + '\'' +
                ", province='" + province + '\'' +
                ", cityId='" + cityId + '\'' +
                ", city='" + city + '\'' +
                ", areaId='" + areaId + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", defaultFlag='" + defaultFlag + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}