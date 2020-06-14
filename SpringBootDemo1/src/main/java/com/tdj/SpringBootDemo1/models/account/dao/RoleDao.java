package com.tdj.SpringBootDemo1.models.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.tdj.SpringBootDemo1.models.account.entity.Role;

@Mapper
public interface RoleDao {

	@Select("select * from role")
	public List<Role> getRoles();
	
	@Insert("insert into role (role_name) values (#{roleNmae})")
	@Options(useGeneratedKeys = true, keyColumn = "role_id", keyProperty = "roleId")
	void insertRole(Role role);
	
}
