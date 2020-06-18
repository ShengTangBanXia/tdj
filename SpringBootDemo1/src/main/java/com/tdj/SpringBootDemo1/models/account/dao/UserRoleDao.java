package com.tdj.SpringBootDemo1.models.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleDao {

	@Delete("delete from user_role where user_id = #{userId}")
	void deleteRolesByUserId(int userId);
	
	@Insert("insert into user_role (user_id, role_id) values(#{userId}, #{roleId})")
	void insertUserRole(@Param("userId") int userId, @Param("roleId") int roleId);
	
}
