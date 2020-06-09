package com.tdj.SpringBootDemo1.models.test.service;

import com.tdj.SpringBootDemo1.models.test.entity.Country;

public interface CountryService {

	Country getCountryByCountryId (int countryId);
	
	Country getCountryByCountryName (String coountryName);
	
}
