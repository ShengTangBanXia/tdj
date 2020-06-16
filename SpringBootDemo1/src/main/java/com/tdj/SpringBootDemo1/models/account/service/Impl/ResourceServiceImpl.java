package com.tdj.SpringBootDemo1.models.account.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdj.SpringBootDemo1.models.account.dao.ResourceDao;
import com.tdj.SpringBootDemo1.models.account.dao.RoleResourceDao;
import com.tdj.SpringBootDemo1.models.account.entity.Resource;
import com.tdj.SpringBootDemo1.models.account.entity.Role;
import com.tdj.SpringBootDemo1.models.account.service.ResourceService;
import com.tdj.SpringBootDemo1.models.common.vo.Result;
import com.tdj.SpringBootDemo1.models.common.vo.Result.resultStatus;
import com.tdj.SpringBootDemo1.models.common.vo.SearchVo;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private RoleResourceDao roleResourceDao;

	@Override
	@Transactional
	public Result<Resource> editResource(Resource resource) {
		
		Resource tempResource = resourceDao.getResourceByResourceName(resource.getResourceName());
		String message = "";
		if (tempResource != null && tempResource.getResourceId() != resource.getResourceId()) {
			return new Result<Resource>(resultStatus.FAILED.status, "Resource name is repeat!!!");
		}

		// 添加 resource
		if (resource.getResourceId() > 0) {	//说明是修改操作
			resourceDao.updateResource(resource);	//修改数据
			roleResourceDao.deletRoleResourceByResourceId(resource.getResourceId());	//删除中间表数据
			message = "Update Success!";
		} else {	//说明是添加操作
			resourceDao.addResource(resource);	//添加数据
			message = "Insert Success!";
		}

		// 添加 roleResource即添加中间表数据
		if (resource.getRoles() != null && !resource.getRoles().isEmpty()) {
			for (Role role : resource.getRoles()) {
				roleResourceDao.addRoleResource(role.getRoleId(), resource.getResourceId());
			}
		}

		return new Result<Resource>(resultStatus.SUCCESS.status, message, resource);
	}

	@Override
	@Transactional
	public Result<Resource> deleteResource(int resourceId) {
		roleResourceDao.deletRoleResourceByResourceId(resourceId);
		resourceDao.deleteResource(resourceId);
		return new Result<Resource>(resultStatus.SUCCESS.status, "");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageInfo<Resource> getResources(SearchVo searchVo) {
		searchVo.initSearchVo();
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo(
				Optional.ofNullable(resourceDao.getResourcesBySearchVo(searchVo))
				.orElse(Collections.emptyList()));
	}

	@Override
	public List<Resource> getResourcesByRoleId(int roleId) {
		return resourceDao.getResourcesByRoleId(roleId);
	}

	@Override
	public Resource getResourceById(int resourceId) {
		return resourceDao.getResourceById(resourceId);
	}
}
