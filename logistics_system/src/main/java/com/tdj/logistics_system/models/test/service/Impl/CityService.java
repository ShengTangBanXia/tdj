package com.tdj.logistics_system.models.test.service.Impl;

import java.util.List;

import com.tdj.logistics_system.models.test.entity.City;

public interface CityService {

	List<City> getCitiesByCountryId(int countryId);
	
}
