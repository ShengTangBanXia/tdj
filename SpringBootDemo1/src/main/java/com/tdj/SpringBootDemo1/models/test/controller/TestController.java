package com.tdj.SpringBootDemo1.models.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdj.SpringBootDemo1.models.test.vo.ApplicationTest;


@Controller
@RequestMapping("/test")
public class TestController {
	
	@Value("${server.port}")
	private int port;
	
	@Value("${com.name}")
	private String name;
	
	@Value("${com.age}")
	private int age;

	@Autowired
	private ApplicationTest applicationTest;
	
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class); 
	
	@RequestMapping("/log")
	@ResponseBody
	public String logTest () {
		
		LOGGER.trace("this is trace log");
		LOGGER.debug("this is debug log");
		LOGGER.info("this is info log");
		LOGGER.warn("this is warn log");
		LOGGER.error("this is error log");
		return "this is log test!";
	}
	
	@RequestMapping("/config")
	@ResponseBody
	public String configInfo () {
	
		StringBuffer sb =  new StringBuffer();
		sb.append(port).append("------")
		.append(name).append("------")
		.append(age).append("------").append("<br>");
		
		sb.append(applicationTest.getAge1()).append("------")
			.append(applicationTest.getName1()).append("------").append("<br>");
		return sb.toString();
	}
	
}
