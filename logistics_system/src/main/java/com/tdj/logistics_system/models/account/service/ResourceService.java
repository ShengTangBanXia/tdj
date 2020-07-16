package com.tdj.logistics_system.models.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.tdj.logistics_system.models.account.entity.Resource;
import com.tdj.logistics_system.models.common.vo.Result;
import com.tdj.logistics_system.models.common.vo.SearchVo;

public interface ResourceService {
	PageInfo<Resource> getResourcesBySearchVo(SearchVo searchVo);
	
	Result<Resource> editResource(Resource resource);

	Resource getResourceById(int resourceId);
	
	Result<Object> deleteResource(int resourceId);
	
	List<Resource> getResourcesByRoleId(int roleId);
}
