package com.tdj.logistics_system.models.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tdj.logistics_system.models.account.entity.Courier;
import com.tdj.logistics_system.models.common.vo.SearchVo;

@Mapper
public interface CourierDao {

	@Insert("insert into courier (Courier_name,Courier_sex,Courier_telephone,courier_entrytime,status) "
			+ "values (#{courierName},#{courierSex},#{courierTelephone},#{courierEntrytime},#{status})")
	@Options(useGeneratedKeys = true, keyColumn = "courier_id", keyProperty = "courierId")
	void insertCourier(Courier courier);
	
	@Select("select * from courier where courier_name = #{courierName}")
	Courier getCourierByCourierName(String courierName);
	
	@Select("<script>" + 
			"select * from courier "
			+ "<where> "
			+ "<if test='keyWord != \"\" and keyWord != null'>"
			+ " and (courier_name like '%${keyWord}%') "
			+ "</if>"
			+ "</where>"
			+ "<choose>"
			+ "<when test='orderBy != \"\" and orderBy != null'>"
			+ " order by ${orderBy} ${sort}"
			+ "</when>"
			+ "<otherwise>"
			+ " order by courier_id desc"
			+ "</otherwise>"
			+ "</choose>"
			+ "</script>")
	List<Courier> getCouriersBySearchVo(SearchVo searchVo);
	
	@Select("select *  from courier where courier_id = #{courierId}")
	Courier getCourierByCourierId(int courierId);
	
	@Update("update courier set courier_name = #{courierName},courier_Sex = #{courierSex},courier_telephone = #{courierTelephone},courier_entrytime = #{courierEntrytime},status = #{status} where courier_id = #{courierId}")
	void updatecourier(Courier courier);

	@Delete("delete from courier where courier_id = #{courierId}")
	void deleteCourierByCourierId(int courierId);
	
	@Update("update courier set courier_name = #{courierName}, courier_image = #{courierImage} where courier_id = #{courierId}")
	void editProfile(Courier courier);
}
