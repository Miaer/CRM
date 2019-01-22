package com.springboot.security.Shiro;

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
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

    /**
     * 添加Shrio内置过滤器
     *  Shrio内置过滤器，可以实现权限相关的拦截器
     *         anon:无需认证（登陆）可以访问
     *         authc：必须认证才可以访问
     *         user：如果使用rememberMe的功能可以直接访问
     *         perms：该资源必须得到资源权限才可以访问
     *         role：该资源必须得到角色权限才可以访问
     */
        Map<String,String> filterMap = new LinkedHashMap();
        filterMap.put("/user/toIndex","authc");
        filterMap.put("/*","authc");
        filterMap.put("/user/login","anon");
        filterMap.put("/toLogin","anon");

        /**
         * 授权过滤器
         * 当前授权被拦截后，shiro默认会自动跳转到未授权页面
         */
        //filterMap.put("","");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/user/toLogin");
        //shiroFilterFactoryBean.setUnauthorizedUrl("user/noAuth");
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm")MyRealm myRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联realm
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name = "myRealm")
    public MyRealm getRealm(){
        return new MyRealm();
    }

}
