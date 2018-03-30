package com.sefarm.common.constant.tips;


import com.sefarm.common.exception.BizExceptionEnum;

/**
 * 返回给前台的错误提示
 *
 * @author mc
 * @date 2018-3-30
 */
public class ErrorTip extends Tip {

    public ErrorTip(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ErrorTip(BizExceptionEnum bizExceptionEnum) {
        this.code = bizExceptionEnum.getCode();
        this.message = bizExceptionEnum.getMessage();
    }
}
