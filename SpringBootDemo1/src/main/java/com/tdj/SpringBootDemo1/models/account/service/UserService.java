package com.tdj.SpringBootDemo1.models.account.service;

import com.tdj.SpringBootDemo1.models.account.entity.User;
import com.tdj.SpringBootDemo1.models.common.vo.Result;

public interface UserService {

	Result<User> insertUser (User user);
	
	User getUserByUserName (String userName);
	
	Result<User> login (User user);
}
