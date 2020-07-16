package com.tdj.logistics_system.models.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

	/**
	 * 127.0.0.1/account/index
	 */
	@RequestMapping("/index")
	public String indexPage() {
				
		return "index";
	}
	
	/**
	 * 127.0.0.1/account/orderEntry
	 */
	@RequestMapping("/orderEntry")
	public String orderEntry() {
		
		return "index";
	}

	/**
	 * 127.0.0.1/order/orderSelect
	 */
	@RequestMapping("/orderSelect")
	public String orderSelect() {
		
		return "index";
	}
	
	@RequestMapping("/test")
	public String testPage() {
		
		return "index";
	}
	
	/**
	 * http://127.0.0.1/account/customers
	 */
	@RequestMapping("/customers")
	public String customersPage() {
		return "index";
	}
	
	/**
	 * http://127.0.0.1/account/couriers
	 */
	@RequestMapping("/couriers")
	public String couriersPage() {
		return "index";
	}
	
	/**
	 * http://127.0.0.1/account/users
	 */
	@RequestMapping("/users")
	public String usersPage() {
		return "index";
	}
	
	/**
	 * http://127.0.0.1/account/resources
	 */
	@RequestMapping("/resources")
	public String resourcesPage() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "indexSimple";
	}
	
	@RequestMapping("/dashboard")
	public String dd() {
		return "index";
	}
	
}
