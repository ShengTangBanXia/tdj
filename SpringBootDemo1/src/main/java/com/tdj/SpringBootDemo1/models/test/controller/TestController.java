package com.tdj.SpringBootDemo1.models.test.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdj.SpringBootDemo1.models.test.entity.City;
import com.tdj.SpringBootDemo1.models.test.entity.Country;
import com.tdj.SpringBootDemo1.models.test.service.CityService;
import com.tdj.SpringBootDemo1.models.test.service.CountryService;
import com.tdj.SpringBootDemo1.models.test.vo.ApplicationTest;


@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private ApplicationTest applicationTest;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private CountryService countryService;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class); 
	
	@RequestMapping("/index")
	public String indexPage (ModelMap modelmap) {
		
		List<City> cities = cityService.getCitiesByCountryId(522);
		cities = cities.stream().limit(10).collect(Collectors.toList());	//将cities数据限制在十条
		Country country = countryService.getCountryByCountryId(522);
		
		modelmap.addAttribute("thymeleafTitle", "you are super man!!!!!");
		modelmap.addAttribute("checked", true);
		modelmap.addAttribute("currentNumber", 99);
		modelmap.addAttribute("changeType", "checkbox");
		modelmap.addAttribute("baiduUrl", "/test/log");
		modelmap.addAttribute("city", cities.get(0));
		modelmap.addAttribute("country", country);
		modelmap.addAttribute("cities", cities);
		modelmap.addAttribute("updateUrl", "/api/city");
		modelmap.addAttribute("shopLogo", 
				"http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
		modelmap.addAttribute("template", "test/index");
		
		return "index";
	}
	
	@Value("${server.port}")
	private int port;
	
	@Value("${com.name}")
	private String name;
	
	@Value("${com.age}")
	private int age;

	
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
