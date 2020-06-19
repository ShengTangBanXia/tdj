package com.tdj.SpringBootDemo1.models.common.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdj.SpringBootDemo1.models.common.vo.Result;
import com.tdj.SpringBootDemo1.models.common.vo.Result.resultStatus;

//全局注解
@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(value = AuthorizationException.class)
	@ResponseBody
	public Result<String> ExceptionHandlerFor403() {
		
		return new Result<String> (resultStatus.FAILED.status, "You have no permission!!!", "/common/403");
	}
	
}
