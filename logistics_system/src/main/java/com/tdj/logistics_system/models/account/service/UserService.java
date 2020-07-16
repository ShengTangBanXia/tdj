package com.tdj.logistics_system.models.account.service;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.tdj.logistics_system.models.account.entity.User;
import com.tdj.logistics_system.models.common.vo.Result;
import com.tdj.logistics_system.models.common.vo.SearchVo;

public interface UserService {
	User getUserByUserName(String userName);
	
	PageInfo<User> getUsersBySearchVo(SearchVo searchVo);
	
	User getUserByUserId(int userId);
	
	Result<Object> deleteUserByUserId(int userId);
	
	Result<User> edit(User user);

	Result<User> login(User user);

	void loginout();

	Result<String> updateUserFile(MultipartFile userImage);

	Result<User> editProfile(User user);
}
