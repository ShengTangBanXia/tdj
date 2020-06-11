package com.tdj.SpringBootDemo1.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "ParameterFilter",urlPatterns = "/**")
public class ParameterFilter implements Filter{

	private final static Logger LOGGER = LoggerFactory.getLogger(ParameterFilter.class); 
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		LOGGER.debug("ParameterFilter init>>>>>>>>>>>>>>>>>>>>>");
	}

	@Override
	public void destroy() {

		LOGGER.debug("ParameterFilter destroy>>>>>>>>>>>>>>>>>>>>>");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.debug("ParameterFilter doFilter>>>>>>>>>>>>>>>>>>>>>");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		//利用包装类进行重写和过滤
		HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpRequest) {

			@Override
			public String getParameter(String name) {
				
				String value = httpRequest.getParameter(name);
				if (StringUtils.isNotBlank(value) && value.contains("fuck")) {	//判断路径参数是否为空以及是否包含违规字符串
					return value.replace("fuck", "***");
				}
				
				return super.getParameter(name);
			}

			@Override
			public String[] getParameterValues(String name) {
				
				String [] values = httpRequest.getParameterValues(name);
				if (values == null) return values;
				for (int i = 0; i < values.length; i++) {
					String temp = values[i];
					if (StringUtils.isNotBlank(temp) && temp.contains("fuck")) {	//判断路径参数是否为空以及是否包含违规字符串
						values[i] = temp.replaceAll("fuck", "***");
					}
				}
				
				return values;
			}
			
			
		};
		
		chain.doFilter(wrapper, response);
		
	}
	
}
