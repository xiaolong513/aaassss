package com.sofb.config;

import com.sofb.authorizing.PersonFilter;
import com.sofb.authorizing.PersonShiroRealm;
import com.sofb.authorizing.RedisSessionDao;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //将自己的验证方式加入容器
    @Bean
    public PersonShiroRealm personShiroRealm() {
        PersonShiroRealm personShiroRealm = new PersonShiroRealm(hashedCredentialsMatcher());
        return personShiroRealm;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager(DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(personShiroRealm());
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, ShiroProperties shiroProperties,
                                                         FormAuthenticationFilter auth, PersonFilter personFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = new HashMap<>();
        filters.put("authc", auth);
        filters.put("person", personFilter);
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> map = new HashMap<>();
        //登出
        map.put("/logout", DefaultFilter.logout.name());
        //对所有用户认证
        map.put("/login", DefaultFilter.authc.name());
        map.put("/**", DefaultFilter.user.name() + ",person");
        //map.put("/**", DefaultFilter.user.name());

        //登录
        shiroFilterFactoryBean.setLoginUrl(shiroProperties.getLoginUrl());
        //首页
        shiroFilterFactoryBean.setSuccessUrl(shiroProperties.getSuccessUrl());
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl(shiroProperties.getUnauthorizedUrl());
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public FormAuthenticationFilter authc() {
        FormAuthenticationFilter authenticationFilter = new FormAuthenticationFilter();
        authenticationFilter.setUsernameParam("userName");
        authenticationFilter.setPasswordParam("password");
        authenticationFilter.setRememberMeParam("rememberMe");
        authenticationFilter.setLoginUrl("/login");
        return authenticationFilter;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5"); //加密方式
        credentialsMatcher.setHashIterations(2);//散列次数
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * session管理
     *
     * @param redisSessionDao
     * @return
     */
    @Bean
    public DefaultWebSessionManager sessionManager(RedisSessionDao redisSessionDao, ShiroProperties shiroProperties) {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionDAO(redisSessionDao);
        defaultWebSessionManager.setGlobalSessionTimeout(shiroProperties.getSessionTimeOut());//单位毫秒
        defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
        return defaultWebSessionManager;
    }

    @Bean
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }


    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
