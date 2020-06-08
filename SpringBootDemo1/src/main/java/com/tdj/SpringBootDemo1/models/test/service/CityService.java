package com.tdj.SpringBootDemo1.models.test.service;

import java.util.List;

import com.tdj.SpringBootDemo1.models.test.entity.City;

public interface CityService {

	List <City> getCitiesByCountryId(int countryId);
	
	City getCityByName(String cityName, String localCityName);
	
}
