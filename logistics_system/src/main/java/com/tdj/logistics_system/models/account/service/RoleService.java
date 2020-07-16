package com.tdj.logistics_system.models.account.service;

import java.util.List;

import com.tdj.logistics_system.models.account.entity.Role;


public interface RoleService {
	List<Role> getRoles();

	List<Role> getRolesByUserId(int userId);
}
