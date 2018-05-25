package com.sefarm.config.web;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 错误页面拦截器 —— 适合内嵌Tomcat或者war方式
 * @author mc
 * @date 2018-5-25
 */
@Component
public class ErrorPageInterceptor extends HandlerInterceptorAdapter {

    /**
     * 网络请求错误码集合
     */
    private List<Integer> errorCodeList = Arrays.asList(500, 404, 400);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (errorCodeList.contains(response.getStatus())) {
            response.sendRedirect("/error/" + response.getStatus());
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
