package com.tdj.SpringBootDemo1.models.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.tdj.SpringBootDemo1.models.account.entity.User;
import com.tdj.SpringBootDemo1.models.common.vo.SearchVo;

@Mapper
public interface UserDao {

	@Insert(" insert into user (user_name, password, create_date) values(#{userName}, #{password}, #{createDate}) ")
	@Options(useGeneratedKeys = true, keyColumn = "user_id", keyProperty = "userId")	//获取绑定插入时的自增主键，以供后面使用
	void insertUser (User user);
	
	@Select("select * from user where user_name = #{userName}")
	User getUserByUserName (String userName);
	
	@Select("<script>" + 
			"select * from user "
			+ "<where> "
			+ "<if test='keyWord != \"\" and keyWord != null'>"
			+ " and (user_name like '%${keyWord}%') "
			+ "</if>"
			+ "</where>"
			+ "<choose>"
			+ "<when test='orderBy != \"\" and orderBy != null'>"
			+ " order by ${orderBy} ${sort}"
			+ "</when>"
			+ "<otherwise>"
			+ " order by user_id desc"
			+ "</otherwise>"
			+ "</choose>"
			+ "</script>")
	List <User> getUsersBySearchVo (SearchVo searchVo);	

}