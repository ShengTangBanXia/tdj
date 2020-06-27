package com.tdj.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class MyZuulFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {

		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		String aaa = request.getParameter("aaa");
		if (aaa == null || aaa == "") {
			// 过滤该请求，不对其进行路由
			requestContext.setSendZuulResponse(false);
			// 设置响应状态码
			requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
			// 设置响应体
			requestContext.setResponseBody("aaa is empty.");
		}
		return null;
	}

	@Override
	public String filterType() {

		return "pre";
	}

	@Override
	public int filterOrder() {

		return 0;
	}

}
