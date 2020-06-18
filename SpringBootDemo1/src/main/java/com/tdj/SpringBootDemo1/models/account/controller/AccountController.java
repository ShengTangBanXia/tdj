package com.tdj.SpringBootDemo1.models.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tdj.SpringBootDemo1.models.account.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private UserService userService;
	
	/**
	 * http://127.0.0.1/account/login
	 */
	@RequestMapping("/login")
	public String loginPage () {
		
		return "indexSimple";
	}
	
	@RequestMapping("/logout")
	public String logOut (ModelMap modelMap) {
		
		userService.logOut();
		//手动设置template，好让拦截器找到要跳转的页面
		modelMap.addAttribute("template", "account/login");
		return "indexSimple";
	}
	
	/**
	 * http://127.0.0.1/account/register
	 */
	@RequestMapping("/register")
	public String registerPage () {
		
		return "indexSimple";
	}
	
	
	/**
	 *  http://127.0.0.1/account/users
	 */
	@RequestMapping("/users")
	public String usersPage() {
		
		return "index";
	}

	/**
	 *  http://127.0.0.1/account/roles
	 */
	@RequestMapping("/roles")
	public String rolesPage() {
		
		return "index";
	}
	
	/**
	 * http://127.0.0.1/account/resources
	 */
	@RequestMapping("/resources")
	public String resourcesPage() {
		return "index";
	}	
	
	/**
	 * http://127.0.0.1/account/resources
	 */
	@RequestMapping("/profile")
	public String profilePage() {
		return "index";
	}
	
}
