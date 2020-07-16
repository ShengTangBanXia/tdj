package com.tdj.logistics_system.models.account.service.Impl;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdj.logistics_system.config.ResourceConfigBean;
import com.tdj.logistics_system.models.account.dao.UserDao;
import com.tdj.logistics_system.models.account.dao.UserRoleDao;
import com.tdj.logistics_system.models.account.entity.Role;
import com.tdj.logistics_system.models.account.entity.User;
import com.tdj.logistics_system.models.account.service.UserService;
import com.tdj.logistics_system.models.common.vo.Result;
import com.tdj.logistics_system.models.common.vo.Result.resultStatus;
import com.tdj.logistics_system.models.common.vo.SearchVo;
import com.tdj.logistics_system.utils.FileUtil;
import com.tdj.logistics_system.utils.MD5Util;

@Service
public class UserServiceImpl implements UserService {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private ResourceConfigBean resourceConfigBean;

	@Override
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}
	@Override
	public PageInfo<User> getUsersBySearchVo(SearchVo searchVo) {
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo<>(
				Optional.ofNullable(userDao.getUsersBySearchVo(searchVo)).orElse(Collections.emptyList()));
	}

	@Override
	public User getUserByUserId(int userId) {
		return userDao.getUserByUserId(userId);
	}

	@Override
	@Transactional
	public Result<User> edit(User user) {
		User userTemp = userDao.getUserByUserName(user.getUserName());
		if (userTemp != null && userTemp.getUserId() != user.getUserId()) {
			return new Result<User>(resultStatus.FAILED.status, "User name is repeat.");
		}
		if (user.getUserId() > 0) {
			userDao.updateUser(user);
			userRoleDao.deleteRolesByUserId(user.getUserId());
		} else {
			user.setPassword(MD5Util.getMD5(user.getPassword()));
			userDao.insertUser(user);
		}
		List<Role> roles = user.getRoles();
		if (roles != null && roles.size() > 0) {
			for (Role role : roles) {
				userRoleDao.insertUserRole(user.getUserId(), role.getRoleId());
			}
		}
		return new Result<User>(resultStatus.SUCCESS.status, "edit success.", user);
	}

	@Override
	public Result<Object> deleteUserByUserId(int userId) {
		userDao.deleteUserByUserId(userId);
		userRoleDao.deleteRolesByUserId(userId);
		return new Result<Object>(resultStatus.SUCCESS.status, "Delete success.");
	}
	@Override
	public Result<User> login(User user) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken  = new UsernamePasswordToken(user.getUserName(),
				MD5Util.getMD5(user.getPassword()));
		try {
			subject.login(usernamePasswordToken );
			subject.checkRoles();
			Session session = subject.getSession();
			//需要将从数据库查询的user存入session，而传入只包括用户名和密码，解决方法：从身份验证器中获取user
			 User userTemp = (User) subject.getPrincipal();
			System.out.println(userTemp);
			session.setAttribute("user", userTemp);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<User>(resultStatus.FAILED.status, "User name or password error.");
		}
		return new Result<User>(resultStatus.SUCCESS.status, "Login success.", user);
	}
	@Override
	public void loginout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
	}
	@Override
	public Result<String> updateUserFile(MultipartFile userImage) {
		if (userImage.isEmpty()) {
			return new Result<String>(resultStatus.SUCCESS.status, "file upload fialed.");
		}
		// 判断上传的文件类型
		if (!FileUtil.isImage(userImage)) {
			return new Result<String>(resultStatus.SUCCESS.status, "File is not a image");
		}
		String originalFilename = userImage.getOriginalFilename();
		// 相对路径
		String relatedPath = resourceConfigBean.getResourcePath() + originalFilename;
		// 本地路径
		String localPath = String.format("%s%s", resourceConfigBean.getLocalPathForWindow(), originalFilename);

		File destFile = new File(localPath);
		try {
			userImage.transferTo(destFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return new Result<>(resultStatus.FAILED.status, "File upload error.");
		}
		return new Result<>(resultStatus.SUCCESS.status, "File upload success.", relatedPath);
	}
	@Override
	@Transactional
	public Result<User> editProfile(User user) {
		User userTemp = getUserByUserName(user.getUserName());
		if (userTemp != null && userTemp.getUserId() != user.getUserId()) {
			return new Result<User>(resultStatus.FAILED.status, "User name is repeat.");
		}
		userDao.editProfile(user);

		return new Result<User>(resultStatus.SUCCESS.status, "Edit success.", user);
	}
}