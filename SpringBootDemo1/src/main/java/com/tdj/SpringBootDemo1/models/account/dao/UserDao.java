package com.tdj.SpringBootDemo1.models.account.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.tdj.SpringBootDemo1.models.account.entity.User;

@Mapper
public interface UserDao {

	@Insert(" insert into user (user_name, password, create_date) values(#{userName}, #{password}, #{createDate}) ")
	@Options(useGeneratedKeys = true, keyColumn = "user_id", keyProperty = "userId")	//获取绑定插入时的自增主键，以供后面使用
	void insertUser (User user);
	
	@Select("select * from user where user_name = #{userName}")
	User getUserByUserName (String userName);
	
}
