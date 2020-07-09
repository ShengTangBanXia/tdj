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
	
	@RequestMapping("/test")
	public String testPage() {
		
		return "index";
	}
	
}
