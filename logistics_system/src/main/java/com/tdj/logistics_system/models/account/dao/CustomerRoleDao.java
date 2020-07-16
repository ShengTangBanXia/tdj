package com.tdj.logistics_system.models.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerRoleDao {
	@Delete("delete from customer_role where customer_id = #{customerId}")
	void deleteRolesByUserId(int customerId);
	
	@Insert("insert into customer_role (customer_id, role_id) values (#{customerId}, #{roleId})")
	void insertCustomerRole(int customerId, int roleId);
}
