package com.tdj.SpringBootDemo1.config.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

	@Autowired
	private MyShiroRealm myShiroRealm;
	
	@Bean
	public DefaultWebSecurityManager securityManager() {
		
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();		
		securityManager.setRealm(myShiroRealm);		
		return securityManager;		
	}
	
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		shiroFilterFactoryBean.setLoginUrl("/account/login");
		shiroFilterFactoryBean.setSuccessUrl("/account/dashboard");
		return shiroFilterFactoryBean;
		
	}
}
