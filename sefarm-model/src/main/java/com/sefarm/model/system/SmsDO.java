package com.sefarm.model.system;

import com.sefarm.common.base.BaseDO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 短信的实体类
 *
 * @author mc
 * @date 2018-3-24
 */
@Table(name = "sefarm_sms")
public class SmsDO extends BaseDO implements Serializable {
    /**
     * 短信ID 
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 接收短信的手机号码
     */
    private String mobile;

    /**
     * 短信内容
     */
    private String content;

    /**
     * 创建时间/发送时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 发送短信目的类型：register-注册; forgetPWD-找回密码;changeMobile-更换手机号;
     */
    private String type;

    /**
     * 短信平台返回代码
     */
    @Column(name = "sms_return_code")
    private String smsReturnCode;

    /**
     * 系统发送结果状态：ing-发送中；ex-异常；ok-发送成功
     */
    @Column(name = "result_code")
    private String resultCode;

    /**
     * 获取短信ID 
     *
     * @return id - 短信ID 
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置短信ID 
     *
     * @param id 短信ID 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取接收短信的手机号码
     *
     * @return mobile - 接收短信的手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置接收短信的手机号码
     *
     * @param mobile 接收短信的手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取短信内容
     *
     * @return content - 短信内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置短信内容
     *
     * @param content 短信内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取创建时间/发送时间
     *
     * @return create_time - 创建时间/发送时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间/发送时间
     *
     * @param createTime 创建时间/发送时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取发送短信目的类型：register-注册; forgetPWD-找回密码;changeMobile-更换手机号;
     *
     * @return type - 发送短信目的类型：register-注册; forgetPWD-找回密码;changeMobile-更换手机号;
     */
    public String getType() {
        return type;
    }

    /**
     * 设置发送短信目的类型：register-注册; forgetPWD-找回密码;changeMobile-更换手机号;
     *
     * @param type 发送短信目的类型：register-注册; forgetPWD-找回密码;changeMobile-更换手机号;
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取短信平台返回代码
     *
     * @return sms_return_code - 短信平台返回代码
     */
    public String getSmsReturnCode() {
        return smsReturnCode;
    }

    /**
     * 设置短信平台返回代码
     *
     * @param smsReturnCode 短信平台返回代码
     */
    public void setSmsReturnCode(String smsReturnCode) {
        this.smsReturnCode = smsReturnCode;
    }

    /**
     * 获取系统发送结果状态：ing-发送中；ex-异常；ok-发送成功
     *
     * @return result_code - 系统发送结果状态：ing-发送中；ex-异常；ok-发送成功
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * 设置系统发送结果状态：ing-发送中；ex-异常；ok-发送成功
     *
     * @param resultCode 系统发送结果状态：ing-发送中；ex-异常；ok-发送成功
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return "SmsDO{" +
                "id=" + id +
                ", mobile='" + mobile + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", type='" + type + '\'' +
                ", smsReturnCode='" + smsReturnCode + '\'' +
                ", resultCode='" + resultCode + '\'' +
                '}';
    }
}