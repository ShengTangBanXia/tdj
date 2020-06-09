package com.tdj.SpringBootDemo1.models.test.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.tdj.SpringBootDemo1.models.common.vo.SearchVo;
import com.tdj.SpringBootDemo1.models.test.entity.City;

public interface CityService {

	List <City> getCitiesByCountryId(int countryId);
	
	City getCityByName(String cityName, String localCityName);
	
	
	PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId);
	
	PageInfo<City> getCitiesBySearchVo (SearchVo searchVo);
}
