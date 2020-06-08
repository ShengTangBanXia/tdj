package com.tdj.SpringBootDemo1.models.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tdj.SpringBootDemo1.models.test.entity.City;
import com.tdj.SpringBootDemo1.models.test.service.CityService;

@RestController
@RequestMapping("/api")
public class CityController {

	@Autowired
	private CityService cityService;
	
	/**
	 * 
	 * 127.0.0.1/api/cities/522
	 */
	@RequestMapping("/cities/{countryId}")
	public List<City> getCitiesByCountryId(@PathVariable int countryId) {
		
		return cityService.getCitiesByCountryId(countryId);
	}
	
	/**
	 * 127.0.0.1/api/city?cityName=shanghai&localCityName=1111
	 */
	@RequestMapping("/city")
	City getCityByName(@RequestParam String cityName,  @RequestParam String localCityName) {
		
		return cityService.getCityByName(cityName, localCityName);
	}
	
	
}
