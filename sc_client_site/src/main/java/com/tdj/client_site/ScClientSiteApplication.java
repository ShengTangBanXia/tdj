package com.tdj.client_site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ScClientSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScClientSiteApplication.class, args);
	}

}
