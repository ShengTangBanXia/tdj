package com.tdj.SpringBootDemo1.models.test.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/applicationTest.properties")
@ConfigurationProperties(prefix="com.test")
public class ApplicationTest {
	
	private String name1;
	private int age1;
	
	public String getName1() {
		return name1;
	}
	
	public void setName1(String name1) {
		this.name1 = name1;
	}
	
	public int getAge1() {
		return age1;
	}
	
	public void setAge1(int age1) {
		this.age1 = age1;
	}
	
}
