package com.tdj.SpringBootDemo1.models.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

	/**
	 * http://127.0.0.1/account/login
	 */
	@RequestMapping("/login")
	public String loginPage () {
		
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
