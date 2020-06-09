package com.tdj.SpringBootDemo1.models.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdj.SpringBootDemo1.models.test.dao.CountryDao;
import com.tdj.SpringBootDemo1.models.test.entity.Country;
import com.tdj.SpringBootDemo1.models.test.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDao countryDao;
	
	@Override
	public Country getCountryByCountryId(int countryId) {
		
		return countryDao.getCountryByCountryId(countryId);
	}

	@Override
	public Country getCountryByCountryName(String coountryName) {
		
		return countryDao.getCountryByCountryName(coountryName);
	}

}
