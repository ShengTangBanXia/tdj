package com.tdj.SpringBootDemo1.models.account.controller;


import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.github.pagehelper.PageInfo;
import com.tdj.SpringBootDemo1.models.account.entity.User;
import com.tdj.SpringBootDemo1.models.account.service.UserService;
import com.tdj.SpringBootDemo1.models.common.vo.Result;
import com.tdj.SpringBootDemo1.models.common.vo.SearchVo;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 127.0.0.1/api/user ------------- post request
	 */
	@PostMapping(value = "/user", consumes = "application/json")
	public Result<User> insertUser(@RequestBody User user) {
		
		return userService.editUser(user);	
	}
	
//	@PostMapping(value = "/user", consumes = "application/json")
//	public User getUserByUserName(@RequestBody String userName) {
//		
//		return userService.getUserByUserName(userName);
//	}
	@PostMapping(value = "/login", consumes = "application/json")
	public Result<User> login(@RequestBody User user) {
		
		return userService.login(user);
	}
	
	/**
	 * 127.0.0.1/api/users ------------- post request
	 */
	@PostMapping(value = "/users", consumes = "application/json")
	public PageInfo<User> getUsersBySearchVo(@RequestBody SearchVo searchVo) {
		
		return userService.getUsersBySearchVo(searchVo);
	}
	
	/**
	 *127.0.0.1/api/user/14
	 */
	@RequestMapping("/user/{userId}")
	public User getUserByUserId(@PathVariable int userId) {
		
		return userService.getUserByUserId(userId);
	}
	
	/**
	 * 127.0.0.1/api/user ------------- put request
	 */
	@PutMapping(value = "/user", consumes = "application/json")
	public Result<User> updateUser(@RequestBody User user) {
		
		return userService.editUser(user);
	}
	
	/**
	 * 127.0.0.1/api/user/13 ------------- delete request
	 */
	@DeleteMapping("/user/{userId}")
	@RequiresPermissions(value = {"/api/user"}, logical = Logical.OR)	//设置资源权限
	public Result<Object> deleteUser(@PathVariable int userId) {
		
		return userService.deleteUser(userId);
	}
	
	/**
	 * 127.0.0.1/api/userImage ---- post
	 */
	@PostMapping(value="/userImage", consumes="multipart/form-data")
	public Result<String> uploadUserImage(@RequestParam MultipartFile userImage, 
			RedirectAttributes redirectAttributes) {
		return userService.uploadUserImage(userImage);
	}

	/**
	 * 127.0.0.1/api/profile ---- put
	 */
	@PutMapping(value = "/profile", consumes = "application/json")
	public Result<User> updateUserProfile(@RequestBody User user) {
		return userService.updateUserProfile(user);
	}
	
}
