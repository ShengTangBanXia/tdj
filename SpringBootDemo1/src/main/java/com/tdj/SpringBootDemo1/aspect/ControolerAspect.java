package com.tdj.SpringBootDemo1.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
public class ControolerAspect {

	private final static Logger LOGGER = LoggerFactory.getLogger(ControolerAspect.class); 	//引入日志
	
	/**
	 * 关联在方法上的切点
	 * 第一个*代表返回类型不限
	 * 第二个*代表module下所有子包
	 * 第三个*代表所有类
	 * 第四个*代表所有方法
	 * (..) 代表参数不限
	 * Order 代表优先级，数字越小优先级越高
	 */
	@Pointcut("execution(public * com.tdj.SpringBootDemo1.models.*.controller.*.*(..))")	//设置切点
	@Order(1)
	public void controllerPointCut() {}
	
	@Before(value = "com.tdj.SpringBootDemo1.aspect.ControolerAspect.controllerPointCut()")	//连接切点，绑定切点
	public void beforeController(JoinPoint joinPoint) {
		
		LOGGER.debug("@@@@@@@@@ Before controller @@@@@@@@@");
		
		//获取请求的参数信息
		ServletRequestAttributes attributes = 
				(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		LOGGER.debug("请求来源：" + request.getRemoteAddr());
		LOGGER.debug("请求URL：" + request.getRequestURL().toString());
		LOGGER.debug("请求方式：" + request.getMethod());
		LOGGER.debug("响应方法：" + joinPoint.getSignature().getDeclaringTypeName() + "." + 
				joinPoint.getSignature().getName());
		LOGGER.debug("请求参数：" + Arrays.toString(joinPoint.getArgs()));
		
	}
	
	@After(value = "com.tdj.SpringBootDemo1.aspect.ControolerAspect.controllerPointCut()")	//连接切点，绑定切点
	public void afterController() {
		
		LOGGER.debug("@@@@@@@@@ After controller @@@@@@@@@-");
	}	
	
	@Around(value = "com.tdj.SpringBootDemo1.aspect.ControolerAspect.controllerPointCut()")	//连接切点，绑定切点
	public Object aroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		LOGGER.debug("@@@@@@@@@ Around controller @@@@@@@@@");
		return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
	}	
	
}
