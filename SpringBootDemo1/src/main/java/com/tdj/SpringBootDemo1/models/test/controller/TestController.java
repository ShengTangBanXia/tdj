package com.tdj.SpringBootDemo1.models.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@PostMapping(value = "/files", consumes = "multipart/form-data")
	public String uploadFiles (RedirectAttributes redirectAttributes, @RequestParam MultipartFile [] files) {
		
		boolean tag = true; 	//用来标记是否所有文件都为空

		for (MultipartFile file : files) {
			if (file.isEmpty()) continue;
			try {
				if (tag) tag = false;
				String destPath = "C:\\Users\\anai\\Desktop\\test1\\" + file.getOriginalFilename();
				File destFile = new File(destPath);
				file.transferTo(destFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("message", "Upload file fail !!!");
				break;
			}	
		}
		if (!tag) redirectAttributes.addFlashAttribute("message", "Upload file success !!!");
		else	redirectAttributes.addFlashAttribute("message", "Please select more than one file !!!");
		return "redirect:/test/index";	
	}
	
	
	@PostMapping(value = "/file", consumes = "multipart/form-data")
	public String uploadFile (RedirectAttributes redirectAttributes, @RequestParam MultipartFile file) {
		
		if (file.isEmpty())	redirectAttributes.addFlashAttribute("message", "Please select file !!!");
		else {
				try {
					String destPath = "C:\\Users\\anai\\Desktop\\test\\" + file.getOriginalFilename();
					File destFile = new File(destPath);
					file.transferTo(destFile);
					redirectAttributes.addFlashAttribute("message", "Upload file success !!!");
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
					redirectAttributes.addFlashAttribute("message", "Upload file fail !!!");
				}			
		}
		return "redirect:/test/index";
	}
	
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
	
	/**
	 * 127.0.0.1/test/desc?key=fuck
	 */
	@RequestMapping("/desc")
	@ResponseBody
	public String testDesc (@RequestParam String key, HttpServletRequest request) {
		
		String key2 = request.getParameter("key");
		return "this is a test model desc!!!!" + key + "=======" + key2;
	}
}
