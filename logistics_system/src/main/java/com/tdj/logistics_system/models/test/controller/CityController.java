package com.tdj.logistics_system.models.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	
}
