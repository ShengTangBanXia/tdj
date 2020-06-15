package com.tdj.SpringBootDemo1.models.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tdj.SpringBootDemo1.models.common.vo.SearchVo;
import com.tdj.SpringBootDemo1.models.test.entity.City;
import com.tdj.SpringBootDemo1.models.test.entity.Country;

@Mapper
public interface CityDao {

	List <City> getCitiesByCountryId(int countryId);
	
	@Select("select * from m_city where country_id = #{countryId}")
	List <City> getCitiesByCountryId2(int countryId);
	
	@Select("select * from m_city where city_name=#{cityName} and local_city_name=#{localCityName}")
	@Results(id = "cityResult", value = {
			@Result(column = "country_id", property = "countryId"), 
			@Result(column = "country_id", property = "country", 
			javaType = Country.class,
			one = @One(select = "com.tdj.SpringBootDemo1.models.test.dao.CountryDao.getCountryByCountryId") )})
	City getCityByName(String cityName, String localCityName);
	
	@Select("<script>" + 
			"select * from m_city "
			+ "<where> "
			+ "<if test='keyWord != \"\" and keyWord != null'>"
			+ " and (city_name like '%${keyWord}%' or local_city_name like '%${keyWord}%') "
			+ "</if>"
			+ "</where>"
			+ "<choose>"
			+ "<when test='orderBy != \"\" and orderBy != null'>"
			+ " order by ${orderBy} ${sort}"
			+ "</when>"
			+ "<otherwise>"
			+ " order by city_id desc"
			+ "</otherwise>"
			+ "</choose>"
			+ "</script>")
	List <City> getCitiesBySearchVo (SearchVo searchVo);
	
	@Insert("insert into m_city (city_name, local_city_name, country_id, date_created) " 
			+ "values(#{cityName}, #{localCityName}, #{countryId}, #{dateCreated})")
	@Options(useGeneratedKeys = true, keyColumn = "city_id", keyProperty = "cityId")	//通过注解来使用插入数据的主键并映射到实体类中的相应属性
	void insertCity(City city);
	
	@Update("update m_city set local_city_name = #{localCityName} where city_id = #{cityId}")
	void updateCity (City city);
	
	@Delete("delete from m_city where city_id = #{cityId}")
	void deleteCity (int cityId);
	
}
