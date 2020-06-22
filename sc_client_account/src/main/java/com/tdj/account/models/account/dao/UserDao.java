package com.tdj.account.models.account.dao;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tdj.account.models.account.entity.User;


@Mapper
public interface UserDao {

	@Select("select * from user where user_id = #{userId}")
	User getUserByUserId (int userId);
	
}
