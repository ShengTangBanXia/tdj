package com.tdj.SpringBootDemo1.models.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tdj.SpringBootDemo1.models.test.entity.Country;
import com.tdj.SpringBootDemo1.models.test.service.CountryService;

@RestController
@RequestMapping("/api")
public class CountryController {

	@Autowired
	private CountryService countryService;
	
	/**
	 *127.0.0.1/api/country/522
	 */
	@RequestMapping("/country/{countryId}")
	public Country getCountryByCountryId( @PathVariable int countryId) {
		
		return countryService.getCountryByCountryId(countryId);
	}
	
	/**
	 * 127.0.0.1/api/country?coountryName=China
	 */
	@RequestMapping("/country")
	public Country getCountryByCountryName( @RequestParam String coountryName) {
		
		return countryService.getCountryByCountryName(coountryName);
	}
	
}
