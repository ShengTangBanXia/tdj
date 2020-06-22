package com.tdj.client_site.models.site.service;

import java.util.List;

import com.tdj.client_site.models.site.entity.City;

public interface CityService {

	List <City> getCitiesByCountryId(int countryId);
	
}
