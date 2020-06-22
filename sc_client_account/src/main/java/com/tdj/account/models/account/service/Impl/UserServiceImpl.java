package com.tdj.account.models.account.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdj.account.models.account.dao.UserDao;
import com.tdj.account.models.account.entity.User;
import com.tdj.account.models.account.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public User getUserByUserId(int userId) {

		return userDao.getUserByUserId(userId);
	}

}
