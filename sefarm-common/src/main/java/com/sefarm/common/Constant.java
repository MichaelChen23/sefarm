package com.sefarm.common;

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

	public static final String STATUS_UNLOCK = "y";

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
	 * 产品的条件查询的默认查询排序字段 add by mc 2018-4-26
	 */
	public static final String PRODUCT_DEFAULT_QUERY_SORT = "sellCount";

	/**
	 * 产品的条件查询的默认查询排序升降幂 add by mc 2018-4-26
	 */
	public static final String PRODUCT_DEFAULT_QUERY_ORDER = "desc";

}
