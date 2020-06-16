package com.tdj.SpringBootDemo1.models.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.tdj.SpringBootDemo1.models.account.entity.Resource;
import com.tdj.SpringBootDemo1.models.common.vo.Result;
import com.tdj.SpringBootDemo1.models.common.vo.SearchVo;



public interface ResourceService {

	Result<Resource> editResource(Resource resource);

	Result<Resource> deleteResource(int resourceId);

	PageInfo<Resource> getResources(SearchVo searchVo);

	List<Resource> getResourcesByRoleId(int roleId);

	Resource getResourceById(int resourceId);
}
