package com.tdj.logistics_system.models.test.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdj.logistics_system.models.test.dao.CityDao;
import com.tdj.logistics_system.models.test.entity.City;

@Service
public class CityServiceImpl implements CityService{

	@Autowired
	private CityDao cityDao;
	
	@Override
	public List<City> getCitiesByCountryId(int countryId) {

		return cityDao.getCitiesByCountryId(countryId);
	}

}
