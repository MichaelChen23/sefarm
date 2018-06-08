package com.sefarm.common.exception;

import com.sefarm.common.base.BaseResponse;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 基础业务异常
 * @author mc
 * @date 2018-5-28
 */
@ControllerAdvice
public class BaseException {

    /**
     * 重复数据key
     */
    private static final String DUPLICATE = "Duplicate";

    /**
     * 数据为空key
     */
    private static final String NULLDATA = "Null";

    /**
     * 移动前端+后台——统一处理异常
     * @param e
     * @param logger
     * @param logMsg
     * @param isExceptionDetail 是否返回详细异常
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse handleException(Exception e, Logger logger, String logMsg, boolean isExceptionDetail) {
        logger.error(logMsg, e.getMessage());
        if (e instanceof RuntimeException) {
            //输出到后台管理系统，返回详细异常信息
            if (isExceptionDetail) {
                return new BaseResponse(400, e.getMessage());
            }
            if (e.getMessage().indexOf(DUPLICATE) != -1) {
                //重复插入主键相同的数据
                return  BaseResponse.getResultByException(BaseExcepitonEnum.EXIST_DATA);
            } else if (e.getMessage().indexOf(NULLDATA) != -1) {
                //空指针异常
                return  BaseResponse.getResultByException(BaseExcepitonEnum.NULL_DATA);
            }
            //运行异常
            return BaseResponse.getResultByException(BaseExcepitonEnum.RUNTIME_ERROR);
        }
        //输出到后台管理系统，返回详细异常信息
        if (isExceptionDetail) {
            return new BaseResponse(400, e.getMessage());
        }
        return BaseResponse.getResultByException(BaseExcepitonEnum.SYSTEM_ERROR);
    }

}
