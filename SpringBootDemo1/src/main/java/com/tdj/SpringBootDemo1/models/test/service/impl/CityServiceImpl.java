package com.tdj.SpringBootDemo1.models.test.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdj.SpringBootDemo1.models.test.dao.CityDao;
import com.tdj.SpringBootDemo1.models.test.entity.City;
import com.tdj.SpringBootDemo1.models.test.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;
	
	@Override
	public List<City> getCitiesByCountryId(int countryId) {
		
		return  Optional.ofNullable(cityDao.getCitiesByCountryId2(countryId))
				.orElse(Collections.emptyList());
	}

	@Override
	public City getCityByName(String cityName, String localCityName) {
		// TODO Auto-generated method stub
		return cityDao.getCityByName(cityName, localCityName);
	}

}
