package com.sunny.layuicms.sys.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.sunny.layuicms.sys.realm.UserRealm;
import lombok.Data;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: fang
 * @Date: 2019/11/7
 *
 * shiro配置文件
 * 作为Spring容器的组件，在YML文件中配置
 */
@Data
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET )
@ConditionalOnClass(value = SecurityManager.class)
@ConfigurationProperties(prefix = "shiro")
public class ShiroAutoConfiguration {

    private static final String SHIRO_DIALECT = "shiroDialect";
    private static final String SHIRO_FILTER = "shiroFilter";
    /**
     * 加密方式(md5)
     */
    private String hashAlgorithmName = "md5";
    /**
     * 散列次数
     */
    private int hashIterations = 2;
    /**
     * 默认的登陆页面
     */
    private String loginUrl = "/index.html";

    private String[] anonUrls;
    private String logOutUrl;
    private String[] authcUrls;

    /**
     * 密码匹配器
     * 设置加密方式和散列次数
     * @return
     */
    @Bean("credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(hashAlgorithmName);
        credentialsMatcher.setHashIterations(hashIterations);
        return credentialsMatcher;
    }

    /**
     * userReam
     * @param credentialsMatcher 注册匹配器
     * @return
     */
    @Bean("userRealm")
    public UserRealm userRealm(CredentialsMatcher credentialsMatcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher);
        return userRealm;
    }

    /**
     * 配置SecurityManager
     * @param userRealm 注入UserRealm
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * shiro内置过滤器：anon（无需认证）authc（必须认证才能访问）
     * @param securityManager
     * @return
     */
    @Bean(SHIRO_FILTER)
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);
        //默认跳转的页面
        bean.setLoginUrl(loginUrl);
        Map<String, String> filterMap = new HashMap<>();
        //设置放行的路径
        if (anonUrls != null && anonUrls.length > 0) {
            for (String anon : anonUrls) {
                filterMap.put(anon, "anon");
            }
        }
        //退出登录的命令
        if (logOutUrl != null) {
            filterMap.put(logOutUrl, "logout");
        }
        //设置要拦截的路径
        if (authcUrls != null && authcUrls.length > 0) {
            for (String authc : authcUrls) {
                filterMap.put(authc, "authc");
            }
        }
        Map<String, Filter> filters = new HashMap<>();
        bean.setFilters(filters);
        //设置要拦截的请求，对应yml配置文件中
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

    /**
     * shiro注册过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean() {
        FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean = new FilterRegistrationBean<>();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        //是否设置Filter过滤器的执行顺序
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName(SHIRO_FILTER);
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    /**
     * 加入注解的使用
     *
     *
     * 开启shiro注解支持,相当于在web.xml文件中开启注解
     * <bean>
     * <bean class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
     * <property name="securityManager" ref="securityManager"/>
     * </bean>
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 利用SpringAOP创建代理
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * shiro和thymeleaf整合，shiro可以控制Thymeleaf的权限控制
     * @return
     */
    @Bean(name = SHIRO_DIALECT)
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

}
