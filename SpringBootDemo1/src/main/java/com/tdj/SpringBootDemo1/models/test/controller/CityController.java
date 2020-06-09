package com.tdj.SpringBootDemo1.models.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.tdj.SpringBootDemo1.models.common.vo.SearchVo;
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
	
	/**
	 * 127.0.0.1/api/cities?currentPage=1&pageSize=5&countryId=522
	 */
	@RequestMapping("/cities")
	public PageInfo<City> getCitiesByPage(@RequestParam int currentPage, 
									@RequestParam int pageSize, @RequestParam int countryId) {
		
		return cityService.getCitiesByPage(currentPage, pageSize, countryId);
	}
	
	//@RequestMapping(value = "/cities", method = RequestMethod.POST, consumes = "application/json")
	/**
	 * 127.0.0.1/api/cities
	 */
	@PostMapping(value = "/cities", consumes = "application/json")	//当请求方式为post时，要指定进入控制器的数据类型
	public PageInfo<City> getCitiesBySearchVo(@RequestBody SearchVo searchVo) {
		
		return cityService.getCitiesBySearchVo(searchVo);
	}
	
}
