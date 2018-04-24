package com.sefarm.common.constant.state;

/**
 * 产品状态的枚举
 *
 * @author mc
 * @date 2018-4-20
 */
public enum ProductStatus {

    NEW("new", "新增"),
    ON("on", "已上架"),
    OFF("off", "已下架"),
    DEL("del", "已删除");

    /**
     * 状态代码
     */
    String code;

    /**
     * 状态名
     */
    String name;

    ProductStatus(String code, String name) {
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
