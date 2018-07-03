package com.sefarm.config.shiro;

import com.sefarm.config.properties.SeFarmProperties;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 权限管理 配置类
 *
 * @author mc
 * @date 2018-6-20
 */
@Configuration
public class ShiroConfig {

    /**
     * 安全管理器
     */
    @Bean
    public DefaultWebSecurityManager securityManager(SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(this.shiroRealm());
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    /**
     * spring session管理器（多机环境）
     */
    @Bean
    @ConditionalOnProperty(prefix = "sefarm", name = "spring-session-open", havingValue = "true")
    public ServletContainerSessionManager servletContainerSessionManager() {
        return new ServletContainerSessionManager();
    }

    /**
     * session 管理器（单机环境）
     */
    @Bean
    @ConditionalOnProperty(prefix = "sefarm", name = "spring-session-open", havingValue = "false")
    public DefaultWebSessionManager defaultWebSessionManager(SeFarmProperties seFarmProperties) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationInterval(seFarmProperties.getSessionValidationInterval() * 1000);
        sessionManager.setGlobalSessionTimeout(seFarmProperties.getSessionInvalidateTime() * 1000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
        cookie.setName("shiroCookie");
//        cookie.setHttpOnly(true);
        sessionManager.setSessionIdCookie(cookie);
        return sessionManager;
    }

    /**
     * 自定义的Realm，相当于安全database
     */
    @Bean
    public ShiroRealm shiroRealm() {
        return new ShiroRealm();
    }

    /**
     * Shiro的过滤器链
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        /**
         * 默认的登陆访问url
         */
        shiroFilter.setLoginUrl("/admin/login");
        /**
         * 登陆成功后跳转的url
         */
        shiroFilter.setSuccessUrl("/admin");
        /**
         * 没有权限跳转的url
         */
        shiroFilter.setUnauthorizedUrl("/error/400");
        /**
         * 配置Shiro拦截器链
         *
         * anon  不需要认证
         * authc 需要认证
         * user  验证通过或RememberMe登录的都可以
         *
         */
        Map<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("/static/**", "anon");
        hashMap.put("/api/kaptcha", "anon");
        hashMap.put("/admin/login", "anon");
        hashMap.put("/api/wechat/**", "anon");
        hashMap.put("/**", "user");
        shiroFilter.setFilterChainDefinitionMap(hashMap);
        return shiroFilter;
    }

    /**
     * 在方法中 注入 securityManager 进行代理控制
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(DefaultWebSecurityManager securityManager) {
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        bean.setArguments(new Object[]{securityManager});
        return bean;
    }

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 启用Shrio授权注解拦截方式，AOP式方法级权限检查
     * 依赖其他bean的初始化
     */
    @Bean
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
