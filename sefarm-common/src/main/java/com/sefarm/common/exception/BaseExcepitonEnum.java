package com.sefarm.common.exception;

/**
 * 基础业务异常枚举类
 * @author mc
 * @date 2018-6-8
 */
public enum BaseExcepitonEnum {

    SYSTEM_ERROR(500, "系统出现异常"),
    RUNTIME_ERROR(500, "系统运行出现异常"),
    EXIST_DATA(400, "已存在重复数据"),
    NULL_DATA(400, "数据为空异常"),
    ;

    /**
     * 返回编码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String msg;

    BaseExcepitonEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
