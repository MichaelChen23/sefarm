package com.sefarm.common.constant.state;

/**
 * 订单状态的枚举
 *
 * @author mc
 * @date 2018-6-13
 */
public enum OrderStatus {

    DONE("done", "完成"),
    PAY("pay", "支付成功"),
    SENDING("sending", "配送中"),
    UNDONE("undone", "未完成");

    /**
     * 状态代码
     */
    String code;

    /**
     * 状态名
     */
    String name;

    OrderStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
