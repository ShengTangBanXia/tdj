package com.tdj.logistics_system.models.account.controller;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.tdj.logistics_system.models.account.entity.User;
import com.tdj.logistics_system.models.account.service.UserService;
import com.tdj.logistics_system.models.common.vo.Result;
import com.tdj.logistics_system.models.common.vo.SearchVo;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 127.0.0.1/api/user
	 */
	@PostMapping(value = "/user", consumes = "application/json")
	public Result<User> insertUser(@RequestBody User user) {
		return userService.edit(user);
	}
	@PostMapping(value = "/users", consumes = "application/json")
	public PageInfo<User> getUsersBySearchVo(@RequestBody SearchVo searchVo) {
		return userService.getUsersBySearchVo(searchVo);
	}

	/**
	 * 127.0.0.1/api/user/3
	 */
	@RequestMapping("/user/{userId}")
	public User getUserByUserId(@PathVariable int userId) {
		return userService.getUserByUserId(userId);
	}
	
	/**
	 * 127.0.0.1/api/user
	 */
	@DeleteMapping("/user/{userId}")
	@RequiresRoles(value={"admin"}, logical=Logical.AND)
	public Result<Object> deleteUserByUserId(@PathVariable int userId) {
		return userService.deleteUserByUserId(userId);
	}
	
	@PutMapping(value = "/user",consumes = "application/json")
	public Result<User> updateUser(@RequestBody User user){
		return userService.edit(user);
	}
	
	
	@PostMapping(value = "/login", consumes = "application/json")
	public Result<User> login(@RequestBody User user) {
		System.err.println("user:"+user);
		return userService.login(user);
	}
	
	
	@PostMapping(value = "userImage",consumes = "multipart/form-data")
	public Result<String> updateUserFile(@RequestBody MultipartFile userImage) {
		return userService.updateUserFile(userImage);
	}
	@PutMapping(value = "/profile",consumes = "application/json")
	public Result<User> updateProfile(@RequestBody User user){
		return userService.editProfile(user);
	}
}
