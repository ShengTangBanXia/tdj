package com.tdj.logistics_system.models.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

	/**
	 * 127.0.0.1/account/orderEntry
	 */
	@RequestMapping("/orderEntry")
	public String orderEntry() {
		
		return "index";
	}
	
	/**
	 * 127.0.0.1/account/orderSelect
	 */
	@RequestMapping("/orderSelect")
	public String orderSelect() {
		
		return "index";
	}
	
}
