package com.tdj.SpringBootDemo1.models.account.service.Impl;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdj.SpringBootDemo1.config.ResourceConfigBean;
import com.tdj.SpringBootDemo1.models.account.dao.UserDao;
import com.tdj.SpringBootDemo1.models.account.dao.UserRoleDao;
import com.tdj.SpringBootDemo1.models.account.entity.Role;
import com.tdj.SpringBootDemo1.models.account.entity.User;
import com.tdj.SpringBootDemo1.models.account.service.UserService;
import com.tdj.SpringBootDemo1.models.common.vo.Result;
import com.tdj.SpringBootDemo1.models.common.vo.Result.resultStatus;
import com.tdj.SpringBootDemo1.models.common.vo.SearchVo;
import com.tdj.SpringBootDemo1.utils.FileUtil;
import com.tdj.SpringBootDemo1.utils.MD5Util;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private ResourceConfigBean resourceConfigBean;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User getUserByUserName(String userName) {
		
		return userDao.getUserByUserName(userName);
	}

	@Override
	public Result<User> login(User user) {
		
//		User temporaryUser = getUserByUserName(user.getUserName());
//		if (temporaryUser == null 
//			|| !temporaryUser.getPassword().equals(MD5Util.getMD5(user.getPassword()))) {	//判断数据库里是否有此用户,以及密码是否相等
//			return new Result <User> (resultStatus.FAILED.status, "The user name does not exist or the password is wrong!!!");
//		}
		
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = 
				new UsernamePasswordToken(user.getUserName(),MD5Util.getMD5(user.getPassword()) );
		usernamePasswordToken.setRememberMe(user.getRememberMe());
		
		try {
			subject.login(usernamePasswordToken);
			subject.checkRoles();
			
		} catch (Exception e) {
			return new Result <User> (resultStatus.FAILED.status, "Login userName or userPassword is error!", user);
		}
		
		return new Result <User> (resultStatus.SUCCESS.status, "Login succeeded!", user);
	}

	@Override
	public void logOut() {
		
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		
	}

	@Override
	public PageInfo<User> getUsersBySearchVo(SearchVo searchVo) {

		searchVo.initSearchVo();	//初始化分页
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());	//开启分页
		
		return new PageInfo<User>(Optional.ofNullable(userDao.getUsersBySearchVo(searchVo))
				.orElse(Collections.emptyList()));
	}

	@Override
	public User getUserByUserId(int userId) {

		return userDao.getUserByUserId(userId);
	}

	@Override
	public Result<Object> deleteUser(int userId) {
		
		userDao.deleteUser(userId);
		userRoleDao.deleteRolesByUserId(userId);
		
		return new Result <Object> (resultStatus.SUCCESS.status, "Delete Success!");
	}

	@Override
	@Transactional	//添加事务，在出现异常时可回滚
	public Result<User> editUser(User user) {
		User temporaryUser = getUserByUserName(user.getUserName());
		String message = "";
		if (temporaryUser != null && temporaryUser.getUserId() != user.getUserId()) {	//判断数据库里是否有此用户以及是插入操作还是修改操作
			return new Result <User> (resultStatus.FAILED.status, "Duplicate user name, please re-enter!!!");
		}
		
		if (user.getUserId() > 0) {	//说明是修改操作不是插入操作			
		//	userDao.updateUser(user);	//管理员只能修改用户的角色信息，不能更改用户信息，用户信息应在profile页面修改			
			userRoleDao.deleteRolesByUserId(user.getUserId());	//删除中间表信息
			message = "Update Success!";
		}else {	//说明是插入操作
			user.setCreateDate(new Date());	//设置日期
			user.setPassword(MD5Util.getMD5(user.getPassword()));	//利用MD5对密码进行加密
			userDao.insertUser(user);
			message = "Insert Success!";
		}
		
		List<Role> roles = user.getRoles();		//获取页面设置的roles信息
		if (roles != null && roles.size() > 0) {	//若roles信息不为空，更新roles信息
			for (Role role : roles) {			
				userRoleDao.insertUserRole(user.getUserId(), role.getRoleId());
			}
		}
		
		return new Result <User> (resultStatus.SUCCESS.status, message, user);
	}

	@Override
	public Result<String> uploadUserImage(MultipartFile userImage) {
		
		if (userImage.isEmpty()) {
			return new Result<>(resultStatus.FAILED.status, "User image is empty.");
		}
		if (!FileUtil.isImage(userImage)) {
			return new Result<>(resultStatus.FAILED.status, "File is not a image.");
		}

		String originalFilename = userImage.getOriginalFilename();
		String relatedPath = resourceConfigBean.getResourcePath() + originalFilename;
		String destPath = String.format("%s%s", resourceConfigBean.getLocalPathForWindows(), originalFilename);
		try {
			File destFile = new File(destPath);
			userImage.transferTo(destFile);

		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			LOGGER.debug(e.getMessage());
			return new Result<>(resultStatus.FAILED.status, "File upload error.");
		}

		return new Result<>(resultStatus.SUCCESS.status, "File upload success.", relatedPath);
	}

	@Override
	@Transactional
	public Result<User> updateUserProfile(User user) {
		
		User userTemp = getUserByUserName(user.getUserName());
		if (userTemp != null && userTemp.getUserId() != user.getUserId()) {
			return new Result<User>(resultStatus.FAILED.status, "User name is repeat.");
		}

		userDao.updateUser(user);

		return new Result<User>(resultStatus.SUCCESS.status, "Edit success.", user);
	}
	
}
