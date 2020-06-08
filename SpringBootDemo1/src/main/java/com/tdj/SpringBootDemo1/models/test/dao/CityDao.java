package com.tdj.SpringBootDemo1.models.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tdj.SpringBootDemo1.models.test.entity.City;

@Mapper
public interface CityDao {

	List <City> getCitiesByCountryId(int countryId);
	
	@Select("select * from m_city where country_id = #{countryId}")
	List <City> getCitiesByCountryId2(int countryId);
	
	@Select("select * from m_city where city_name=#{cityName} and local_city_name=#{localCityName}")
	City getCityByName(String cityName, String localCityName);
}
