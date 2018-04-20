package com.sefarm.common.constant.state;

/**
 * 订单配送状态的枚举
 *
 * @author mc
 * @date 2018-4-20
 */
public enum OrderDeliveryStatus {

    NEW("new", "新增"),
    CHECK("check", "审核中"),
    READY("ready", "待发货"),
    DELIVERY("delivery", "已发货"),
    RECEIVE("receive", "已签收"),
    CANCEL("cancel", "已取消");

    /**
     * 状态代码
     */
    String code;

    /**
     * 状态名
     */
    String name;

    OrderDeliveryStatus(String code, String name) {
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
