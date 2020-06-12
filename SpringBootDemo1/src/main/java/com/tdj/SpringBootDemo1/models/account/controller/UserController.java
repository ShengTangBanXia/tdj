package com.tdj.SpringBootDemo1.models.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdj.SpringBootDemo1.models.account.entity.User;
import com.tdj.SpringBootDemo1.models.account.service.UserService;
import com.tdj.SpringBootDemo1.models.common.vo.Result;

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
		
		return userService.insertUser(user);	
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
	
}
