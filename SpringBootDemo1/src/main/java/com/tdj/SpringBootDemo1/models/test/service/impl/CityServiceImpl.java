package com.tdj.SpringBootDemo1.models.test.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdj.SpringBootDemo1.models.common.vo.Result;
import com.tdj.SpringBootDemo1.models.common.vo.SearchVo;
import com.tdj.SpringBootDemo1.models.test.dao.CityDao;
import com.tdj.SpringBootDemo1.models.test.entity.City;
import com.tdj.SpringBootDemo1.models.test.service.CityService;
import com.tdj.SpringBootDemo1.utils.RedisUtils;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;
	
	@Autowired
	private RedisUtils redisUtils;
	
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

	@Override
	public PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId) {
		//开启分页
		PageHelper.startPage(currentPage, pageSize);
		
		return new PageInfo<City>(Optional.ofNullable(cityDao.getCitiesByCountryId2(countryId))
				.orElse(Collections.emptyList()));
	}

	@Override
	public PageInfo<City> getCitiesBySearchVo(SearchVo searchVo) {

		searchVo.initSearchVo();
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo<City>(Optional.ofNullable(cityDao.getCitiesBySearchVo(searchVo))
				.orElse(Collections.emptyList()));
	}

	@Override
	public Result<City> insertCity(City city) {
		
		city.setDateCreated(new Date());
		cityDao.insertCity(city);
		
		return new Result<City>(Result.resultStatus.SUCCESS.status, "insert success", city);
	}

	@Override
	@Transactional	//添加事务
	public Result<City>  updateCity(City city) {
		
		cityDao.updateCity(city);
		return new Result<City>(Result.resultStatus.SUCCESS.status, "update success", city);
	}

	@Override
	public Result<Object> deleteCity(int cityId) {

		cityDao.deleteCity(cityId);		
		return new Result<Object>(Result.resultStatus.SUCCESS.status, "delete success");
	}

	@Override
	public Object migrateCitiesByCountryId(int countryId) {

		List<City> cities = getCitiesByCountryId(countryId);
		redisUtils.set("cities", cities);
		return redisUtils.get("cities");
	}

}
