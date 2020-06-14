package com.tdj.SpringBootDemo1.models.account.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdj.SpringBootDemo1.models.account.dao.RoleDao;
import com.tdj.SpringBootDemo1.models.account.entity.Role;
import com.tdj.SpringBootDemo1.models.account.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> getRoles() {

		return Optional.ofNullable(roleDao.getRoles()).orElse(Collections.emptyList());
	}
	
	
}
