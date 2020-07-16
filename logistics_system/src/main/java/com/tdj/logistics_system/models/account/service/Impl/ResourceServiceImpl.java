package com.tdj.logistics_system.models.account.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdj.logistics_system.models.account.dao.ResourceDao;
import com.tdj.logistics_system.models.account.dao.RoleResourceDao;
import com.tdj.logistics_system.models.account.entity.Resource;
import com.tdj.logistics_system.models.account.entity.Role;
import com.tdj.logistics_system.models.account.service.ResourceService;
import com.tdj.logistics_system.models.common.vo.Result;
import com.tdj.logistics_system.models.common.vo.Result.resultStatus;
import com.tdj.logistics_system.models.common.vo.SearchVo;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private RoleResourceDao roleResourceDao;

	@Override
	public PageInfo<Resource> getResourcesBySearchVo(SearchVo searchVo) {
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo<>(
				Optional.ofNullable(resourceDao.getResourcesBySearchVo(searchVo)).orElse(Collections.emptyList()));
	}
	
	

	@Override
	public Resource getResourceById(int resourceId) {
		return resourceDao.getResourceById(resourceId);
	}



	@Override
	public Result<Resource> editResource(Resource resource) {
		Resource resourceTemp = resourceDao.getResourceByPermission(resource.getPermission());
		if (resourceTemp != null && resourceTemp.getResourceId() != resource.getResourceId()) {
			return new Result<Resource>(resultStatus.FAILED.status, "resource name is repeat.");
		}
		if (resource.getResourceId() > 0) {
			resourceDao.updateResource(resource);
			roleResourceDao.deletRoleResourceByResourceId(resource.getResourceId());
		} else {
			resourceDao.insertResource(resource);
		}
		List<Role> roles = resource.getRoles();
		if (roles != null && roles.size() > 0) {
			for (Role role : roles) {
				roleResourceDao.insertRoleResource(role.getRoleId(), resource.getResourceId());
			}
		}
		return new Result<Resource>(resultStatus.SUCCESS.status,"editResource success",resource);
	}



	@Override
	public Result<Object> deleteResource(int resourceId) {
		resourceDao.deleteResource(resourceId);
		return new Result<Object>(resultStatus.SUCCESS.status,"delete success");
	}



	@Override
	public List<Resource> getResourcesByRoleId(int roleId) {
		return resourceDao.getResourcesByRoleId(roleId);
	}
	
	
}
