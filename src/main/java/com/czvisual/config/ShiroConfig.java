package com.czvisual.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * 第一个bean:realm  数据域
     * <p>
     * 第二个bean：SecurityManager 安全管理器
     * <p>
     * 第三个bean：shirofileterfactorybean
     * <p>
     * 第四个：密码匹配器
     * <p>
     * 第五个bean：ShiroDialect
     */

    // <bean id="">   name对应bean容器的id
    @Bean(name = "userRealm")
    public UserRealm getUserRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> filtermap = new LinkedHashMap<String, String>();

        filtermap.put("/login", "anon");
        filtermap.put("/register", "anon");
        filtermap.put("/toRegister", "anon");

        filtermap.put("/css/**", "anon");
        filtermap.put("/layui/**", "anon");
        filtermap.put("/js/**", "anon");
        filtermap.put("/images/**", "anon");
        filtermap.put("*.png", "anon");
        filtermap.put("*jpg", "anon");

        filtermap.put("/logout", "logout");

        //filtermap.put("/index*","authc");
        //filtermap.put("/index/test1","anon,roles[gs]");
        //filtermap.put("/index/test2","roles[gs]");
        filtermap.put("/hsManage/overview","perms[hsManage:overview]");
        filtermap.put("/hsManage/tableview","perms[hsManage:tableview]");
        filtermap.put("/dataManage/hs","perms[dataManage:operateHsInfo]");
        filtermap.put("/dataManage/operatehsdata","perms[dataManage:operateHsData]");
        filtermap.put("/chartAnalysis/*","perms[chartAnalysis]");
        filtermap.put("/user/manageUser","perms[user:manageUser]");
        filtermap.put("/user/updateProfile","perms[user:updateProfile]");


        //未认证都不允许通过
        filtermap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filtermap);
        //如果访问的页面未认证   跳转到登陆页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/unAuthorized");
        return shiroFilterFactoryBean;
    }

    //用来跟加密的密码进行比对的bean

    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //设置比较规则
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //迭代2次
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    //shiro 与thymeleaf的整合
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }


    /*
    * 如果需要通过注解来授权，就要创建这两个Bean，但是采用Filter就不用
    * 采用注解进行权限控制太分散
    * */
    //@Bean
    //public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
    //    DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
    //    advisorAutoProxyCreator.setProxyTargetClass(true);
    //    return advisorAutoProxyCreator;
    //}
    //
    //@Bean
    //public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
    //    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
    //    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    //    return authorizationAttributeSourceAdvisor;
    //}



}
