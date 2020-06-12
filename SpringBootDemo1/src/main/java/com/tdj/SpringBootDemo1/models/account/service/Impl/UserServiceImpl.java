package com.tdj.SpringBootDemo1.models.account.service.Impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdj.SpringBootDemo1.models.account.dao.UserDao;
import com.tdj.SpringBootDemo1.models.account.entity.User;
import com.tdj.SpringBootDemo1.models.account.service.UserService;
import com.tdj.SpringBootDemo1.models.common.vo.Result;
import com.tdj.SpringBootDemo1.models.common.vo.Result.resultStatus;
import com.tdj.SpringBootDemo1.utils.MD5Util;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional	//添加事务，在出现异常时可回滚
	public Result<User> insertUser(User user) {
		
		User temporaryUser = getUserByUserName(user.getUserName());
		
		if (temporaryUser != null) {	//判断数据库里是否有此用户，来判断用户名是否重复
			return new Result <User> (resultStatus.FAILED.status, "Duplicate user name, please re-enter!!!");
		}
		
		user.setCreateDate(new Date());	//设置日期
		user.setPassword(MD5Util.getMD5(user.getPassword()));	//利用MD5对密码进行加密
		userDao.insertUser(user);	//向数据库中插入用户
		
		return new Result <User> (resultStatus.SUCCESS.status, "Insert Success!", user);
	}

	@Override
	public User getUserByUserName(String userName) {
		
		return userDao.getUserByUserName(userName);
	}

	@Override
	public Result<User> login(User user) {
		
		User temporaryUser = getUserByUserName(user.getUserName());
		if (temporaryUser == null 
			|| !temporaryUser.getPassword().equals(MD5Util.getMD5(user.getPassword()))) {	//判断数据库里是否有此用户,以及密码是否相等
			return new Result <User> (resultStatus.FAILED.status, "The user name does not exist or the password is wrong!!!");
		}
		return new Result <User> (resultStatus.SUCCESS.status, "Login succeeded!", temporaryUser);
	}

}
