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
	
	public String orderEntry() {
		
		return "orderEntry";
	}
	
	
}
