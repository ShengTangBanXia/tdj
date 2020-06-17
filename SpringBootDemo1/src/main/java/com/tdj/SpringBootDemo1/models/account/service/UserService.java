package com.tdj.SpringBootDemo1.models.account.service;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.tdj.SpringBootDemo1.models.account.entity.User;
import com.tdj.SpringBootDemo1.models.common.vo.Result;
import com.tdj.SpringBootDemo1.models.common.vo.SearchVo;

public interface UserService {
	
	User getUserByUserName (String userName);
	
	Result<User> login (User user);
	
	PageInfo<User> getUsersBySearchVo (SearchVo searchVo);	
	
	User getUserByUserId (int userId);
	
	Result<Object> deleteUser (int userId);
	
	Result<User> editUser(User user);
	
	Result<String> uploadUserImage(MultipartFile userImage);
	
	Result<User> updateUserProfile(User user);
}
