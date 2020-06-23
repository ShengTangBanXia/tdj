package com.tdj.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication	//spring boot
@EnableDiscoveryClient	//client
@EnableHystrix			//hystrix, 容错
@EnableFeignClients		//实现均衡和容错处理
public class ScClientAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScClientAccountApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate () {	//用来调用其他项目接口
		
		return new RestTemplate();
	}
	
}
