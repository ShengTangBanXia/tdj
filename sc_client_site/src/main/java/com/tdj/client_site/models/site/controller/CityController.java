package com.tdj.client_site.models.site.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdj.client_site.models.site.entity.City;
import com.tdj.client_site.models.site.service.CityService;

@RestController
@RequestMapping("/api")
public class CityController {

	@Autowired
	private CityService cityService;
	
	/**
	 * http://127.0.0.1:8761/api/cities/522
	 */
	@RequestMapping("/cities/{countryId}")
	public List<City> getCitiesByCountryId(@PathVariable int countryId) {
		
		return cityService.getCitiesByCountryId(countryId);
	}
	
}
