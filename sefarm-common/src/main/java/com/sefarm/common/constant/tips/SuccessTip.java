package com.sefarm.common.constant.tips;

/**
 * 返回给前台的成功提示
 *
 * @author mc
 * @date 2018-3-30
 */
public class SuccessTip extends Tip {
	
	public SuccessTip(){
		super.code = 200;
		super.message = "操作成功";
	}
}
