package com.tdj.SpringBootDemo1.models.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdj.SpringBootDemo1.vo.ApplicationTest;

@Controller
public class TestController {
	
	@Value("${server.port}")
	private int port;
	
	@Value("${com.name}")
	private String name;
	
	@Value("${com.age}")
	private int age;

	@Autowired
	private ApplicationTest applicationTest;
	
	@RequestMapping("/test/config")
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
