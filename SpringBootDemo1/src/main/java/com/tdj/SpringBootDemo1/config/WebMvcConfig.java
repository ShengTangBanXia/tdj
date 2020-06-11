package com.tdj.SpringBootDemo1.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tdj.SpringBootDemo1.filter.ParameterFilter;
import com.tdj.SpringBootDemo1.interceptor.UrlInterceptor;

@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${server.http.port}")
	private int httpPort;
	
	@Autowired
	private ResourceConfigBean resourceConfigBean;
	
	@Autowired
	private UrlInterceptor urlInterceptor;
	
	@Bean
	public Connector connector () {
		Connector connector = new Connector();
		connector.setScheme("http");	//配置传输协议
		connector.setPort(httpPort);	//配置端口,要与https端口不一致
		
		return connector;
	}
	
	@Bean
	public ServletWebServerFactory servletWebServerFactory () {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.addAdditionalTomcatConnectors(connector());
		return factory;
	}
	
	@Bean
	public FilterRegistrationBean<ParameterFilter> filter () {
		
		FilterRegistrationBean<ParameterFilter> register = new FilterRegistrationBean<ParameterFilter>();	//注册过滤器
		register.setFilter(new ParameterFilter());	//设置过滤器  	
		
		return register;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(urlInterceptor).addPathPatterns("/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		String systemName = System.getProperty("os.name");
		if (systemName.toLowerCase().startsWith("win"))
			registry.addResourceHandler(resourceConfigBean.getResourcePath())
			.addResourceLocations("file:" + resourceConfigBean.getLocalPathForWindows());
		else
			registry.addResourceHandler(resourceConfigBean.getResourcePath())
			.addResourceLocations("file:" + resourceConfigBean.getLocalPathForLinux());
		
	}
	
	
	
}
