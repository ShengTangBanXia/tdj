package com.tdj.SpringBootDemo1.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {

	@Autowired
	private MyShiroRealm myShiroRealm;
	
	@Bean
	public DefaultWebSecurityManager securityManager() {	//shiro核心组件，注入realm
		
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();		
		securityManager.setRealm(myShiroRealm);		
		return securityManager;		
	}
	
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {	//将securityManager注入ShiroFilterFactoryBean
		
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		shiroFilterFactoryBean.setLoginUrl("/account/login");	//没有登录时跳转至登录页面
		shiroFilterFactoryBean.setSuccessUrl("/account/dashboard");	//登陆成功后跳转至相应页面
		
		//为各种路径设置权限
		Map<String, String> map = new LinkedHashMap<String, String>();
		//静态资源路径，不需要登录就能访问
		map.put("/static/**", "anon");
		map.put("/build/**", "anon");
		map.put("/images/**", "anon");
		//map.put("/shopping/**", "anon");
		map.put("/vendors/**", "anon");
		
		map.put("/account/login", "anon");
		map.put("/account/register", "anon");
		map.put("/api/login", "anon");
		map.put("/api/user", "anon");
		map.put("/test/**", "anon");

		// 如果使用“记住我功能”，则采用user规则，如果必须要用户登录，则采用authc规则
		map.put("/**", "authc");
//		map.put("/pay/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		
		return shiroFilterFactoryBean;		
	}
	
	/**
	 * --注册shiro方言，让thymeleaf支持shiro标签
	 */
	@Bean
	public ShiroDialect shiroDialect(){
		return new ShiroDialect();
	}

	/**
	 * --自动代理类，支持Shiro的注解
	 */
	@Bean
	@DependsOn({"lifecycleBeanPostProcessor"})
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

    /**
     * --开启Shiro的注解
     */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}
}
