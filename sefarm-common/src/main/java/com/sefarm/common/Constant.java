package com.sefarm.common;

import java.math.BigDecimal;

/**
 * 常量类
 * @author mc
 * @date 2018-3-18
 */
public abstract class Constant {

	public static final Integer SUCCESS_CODE = 200;

	public static final String SUCCESS_MSG = "成功";

	public static final Integer FAIL_CODE = 500;

	public static final String FAIL_MSG = "失败";

	public static final Integer DEFAULT_PAGE_INDEX = 1;

	public static final Integer DEFAULT_ROWS = 10;

	/**
	 * 用于status，启用就是没锁住，y
	 */
	public static final String STATUS_UNLOCK = "y";

	/**
	 * 用于status，禁用就是锁住，n
	 */
	public static final String STATUS_LOCK = "n";

	/**
	 * 升幂排序
	 */
	public static final String ORDER_ASC = "asc";

	/**
	 * 降幂排序
	 */
	public static final String ORDER_DESC = "desc";

	/**
	 * 系统用户默认密码
	 */
	public static final String DEFAULT_SYS_USER_PWD = "888888";

	/**
	 * DUBBO接口的timeout时间 add by mc 2018-4-24
	 */
	public static final int DUBBO_TIME_OUT = 100000;

	/**
	 * 共用条件查询的默认查询排序字段 add by mc 2018-5-3
	 */
	public static final String DEFAULT_QUERY_SORT = "createTime";

	/**
	 * 共用条件查询的默认查询排序升降幂 add by mc 2018-5-3
	 */
	public static final String DEFAULT_QUERY_ORDER = "desc";

	/**
	 * 产品的条件查询的默认查询排序字段 add by mc 2018-4-26
	 */
	public static final String PRODUCT_DEFAULT_QUERY_SORT = "sellCount";

	/**
	 * 产品的条件查询的默认查询排序升降幂 add by mc 2018-4-26
	 */
	public static final String PRODUCT_DEFAULT_QUERY_ORDER = "desc";

	/**
	 * 事务默认timeout值，add by mc 2018-4-29
	 */
	public static final int DEFAULT_TRANSACTION_TIMEOUT = 36000;

	/**
	 * 用户帐号默认帐号类型为wechat微信
	 */
	public static final String ACCOUNT_DEFAULT_TYPE = "wechat";

	/**
	 * 用户帐号默认等级为R1
	 */
	public static final String ACCOUNT_DEFAULT_RANK = "R1";

	/**
	 * 用户帐号默认金额为0
	 */
	public static final BigDecimal ACCOUNT_DEFAULT_AMOUNT = BigDecimal.valueOf(0);

	/**
	 * 微信sefarm appID
	 */
	public static final String WECHAT_APPID = "";

	/**
	 * 微信sefarm appSecret
	 */
	public static final String WECHAT_APPSECRET = "";

	/**
	 * 微信支付商户号
	 */
	public static final String WECHAT_MCH_ID = "";

	/**
	 * 微信支付API密钥
	 */
	public static final String WECHAT_API_KEY = "";

	/**
	 * 微信获取accessToken url
	 */
	public static final String WECHAT_GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

	/**
	 * 微信获取accessToken的grant_type
	 */
	public static final String WECHAT_GRANT_TYPE = "authorization_code";

	/**
	 * 微信根据accessToken 获取userInfo url
	 */
	public static final String WECHAT_GET_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";

	/**
	 * 微信获取userInfo的lang
	 */
	public static final String WECHAT_LANG_TYPE = "zh_CN";

}
