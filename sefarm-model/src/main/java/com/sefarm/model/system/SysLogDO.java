package com.sefarm.model.system;

import com.sefarm.common.base.BaseDO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志的实体类
 *
 * @author mc
 * @date 2018-3-24
 */
@Table(name = "sefarm_sys_log")
public class SysLogDO extends BaseDO implements Serializable {
    /**
     * 系统日志ID 
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 日志类型：sys-系统后台日志，user-用户日志，默认user 
     */
    private String type;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 代理
     */
    private String agent;

    /**
     * 请求URI
     */
    @Column(name = "request_uri")
    private String requestUri;

    /**
     * 操作方式
     */
    private String method;

    /**
     * 描述
     */
    private String description;

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
     * 操作提交的数据
     */
    private String params;

    /**
     * 异常信息
     */
    private String exception;

    /**
     * 获取系统日志ID 
     *
     * @return id - 系统日志ID 
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置系统日志ID 
     *
     * @param id 系统日志ID 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取日志类型：sys-系统后台日志，user-用户日志，默认user 
     *
     * @return type - 日志类型：sys-系统后台日志，user-用户日志，默认user 
     */
    public String getType() {
        return type;
    }

    /**
     * 设置日志类型：sys-系统后台日志，user-用户日志，默认user 
     *
     * @param type 日志类型：sys-系统后台日志，user-用户日志，默认user 
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取IP地址
     *
     * @return ip - IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置IP地址
     *
     * @param ip IP地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取代理
     *
     * @return agent - 代理
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 设置代理
     *
     * @param agent 代理
     */
    public void setAgent(String agent) {
        this.agent = agent;
    }

    /**
     * 获取请求URI
     *
     * @return request_uri - 请求URI
     */
    public String getRequestUri() {
        return requestUri;
    }

    /**
     * 设置请求URI
     *
     * @param requestUri 请求URI
     */
    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    /**
     * 获取操作方式
     *
     * @return method - 操作方式
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置操作方式
     *
     * @param method 操作方式
     */
    public void setMethod(String method) {
        this.method = method;
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
     * 获取操作提交的数据
     *
     * @return params - 操作提交的数据
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置操作提交的数据
     *
     * @param params 操作提交的数据
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * 获取异常信息
     *
     * @return exception - 异常信息
     */
    public String getException() {
        return exception;
    }

    /**
     * 设置异常信息
     *
     * @param exception 异常信息
     */
    public void setException(String exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "SysLogDO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", ip='" + ip + '\'' +
                ", agent='" + agent + '\'' +
                ", requestUri='" + requestUri + '\'' +
                ", method='" + method + '\'' +
                ", description='" + description + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", params='" + params + '\'' +
                ", exception='" + exception + '\'' +
                '}';
    }
}