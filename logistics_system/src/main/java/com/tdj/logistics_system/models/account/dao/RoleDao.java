package com.tdj.logistics_system.models.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tdj.logistics_system.models.account.entity.Role;

@Mapper
public interface RoleDao {
	@Select("select * from role")
	List<Role> getRoles();
	
	@Select("select * from role role left join customer_role customerRole "
			+ "on role.role_id = customerRole.role_id where customerRole.customer_id = #{customerId}")
	List<Role> getRolesByCustomerId(int customerId);
	
	@Select("select * from role role left join user_role userRole "
			+ "on role.role_id = userRole.role_id where userRole.user_id = #{userId}")
	List<Role> getRolesByUserId(int userId);
	
	@Select("select * from role role left join role_resource roleResource "
			+ "on role.role_id = roleResource.role_id where roleResource.resource_id = #{resourceId}")
	List<Role> getRolesByResourceId(int resourceId);
}
