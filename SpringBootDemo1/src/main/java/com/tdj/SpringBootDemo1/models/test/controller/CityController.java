package com.tdj.SpringBootDemo1.models.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.tdj.SpringBootDemo1.models.common.vo.Result;
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
	 * 127.0.0.1/api/cities/522 ----------get
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
	
	/**
	 * 127.0.0.1/api/city ------------post
	 */
	@PostMapping(value = "/city", consumes = "application/json")
	public Result<City> insertCity(@RequestBody City city) {
		
		return cityService.insertCity(city);
	}
	
	/**
	 * 127.0.0.1/api/city -----------put
	 */
	@PutMapping(value = "/city", consumes = "application/x-www-form-urlencoded")
	public Result<City>  updateCity(@ModelAttribute City city) {
		
		return cityService.updateCity(city);
	}
	
	/**
	 * 127.0.0.1/api/city/2258 ---------delete
	 */
	@DeleteMapping("/city/{cityId}")
	public Result<Object> deleteCity(@PathVariable int cityId) {
		
		return cityService.deleteCity(cityId);
	}
	
	/**
	 *127.0.0.1/api/redis/cities/522 ------------get
	 */
	@RequestMapping("/redis/cities/{countryId}")
	public Object migrateCitiesByCountryId(@PathVariable int countryId) {
		
		return cityService.migrateCitiesByCountryId(countryId);
	}
	
}
