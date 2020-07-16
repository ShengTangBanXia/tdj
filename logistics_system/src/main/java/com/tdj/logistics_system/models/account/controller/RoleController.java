package com.tdj.logistics_system.models.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdj.logistics_system.models.account.entity.Role;
import com.tdj.logistics_system.models.account.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	/**
	 * 127.0.0.1/api/roles
	 */
	@RequestMapping("/roles")
	List<Role> getRoles() {
		return roleService.getRoles();
	}
}
