package com.tdj.logistics_system.models.test.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

	@Override
	public PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId) {
		//开启分页
		PageHelper.startPage(currentPage, pageSize);
		
		return new PageInfo<City>(Optional.ofNullable(cityDao.getCitiesByCountryId(countryId))
				.orElse(Collections.emptyList()));
	}

}
