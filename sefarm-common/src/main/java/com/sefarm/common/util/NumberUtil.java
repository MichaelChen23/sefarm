package com.sefarm.common.util;

/**
 * 处理数字、号码工具类
 * add by mc 2018-4-29
 */
public class NumberUtil {

    /**
     * 获取唯一订单号
     * @return
     */
    public static String getUniqueOrderNo() {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        return "SE" + idWorker.nextId();
    }
}
