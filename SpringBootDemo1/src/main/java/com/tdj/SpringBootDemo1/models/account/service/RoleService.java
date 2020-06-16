package com.tdj.SpringBootDemo1.models.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.tdj.SpringBootDemo1.models.account.entity.Role;
import com.tdj.SpringBootDemo1.models.common.vo.Result;
import com.tdj.SpringBootDemo1.models.common.vo.SearchVo;

public interface RoleService {

	public List<Role> getRoles();
	
	Result<Role> editRole(Role role);

	Result<Role> deleteRole(int roleId);

	PageInfo<Role> getRoles(SearchVo searchVo);

	List<Role> getRolesByUserId(int userId);

	List<Role> getRolesByResourceId(int resourceId);

	Role getRoleById(int roleId);
}
