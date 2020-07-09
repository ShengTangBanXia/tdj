package com.tdj.logistics_system.models.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.tdj.logistics_system.models.test.entity.City;
import com.tdj.logistics_system.models.test.service.Impl.CityService;

@RestController
@RequestMapping("/test")
public class CityController {

	@Autowired
	private CityService cityService;
	
	/**
	 * 127.0.0.1/test/cities/522 ----------get
	 */
	@RequestMapping("/cities/{countryId}")
	public List<City> getCitiesByCountryId(@PathVariable int countryId) {
		
		return cityService.getCitiesByCountryId(countryId);
	}
	
	@RequestMapping("/log")
	@ResponseBody
	public String testLog() {
		
		return "This is a test page!!!";
	}
	
	/**
	 *127.0.0.1/test/cities?currentPage=1&pageSize=5&countryId=522 ----------get
	 */
	@RequestMapping("/cities")
	public PageInfo<City> getCitiesByPage(@RequestParam int currentPage, 
			@RequestParam int pageSize, @RequestParam int countryId) {
		
		return cityService.getCitiesByPage(currentPage, pageSize, countryId);
	}
	
	
	
	
}
