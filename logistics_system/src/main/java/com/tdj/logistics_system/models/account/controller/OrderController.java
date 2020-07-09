package com.tdj.logistics_system.models.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.tdj.logistics_system.models.account.entity.Order;
import com.tdj.logistics_system.models.account.service.OrderService;
import com.tdj.logistics_system.models.common.vo.SearchVo;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	/**
	 * 127.0.0.1/order/orderEntry
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
	
	/**
	 * 127.0.0.1/order/orders-------------->post
	 */
	@PostMapping(value = "/orders", consumes = "application/json")
	@ResponseBody
	public PageInfo<Order> getOrdersBySearchVo(@RequestBody  SearchVo searchVo) {
		System.out.println( orderService.getOrdersBySearchVo(searchVo));
		
		return orderService.getOrdersBySearchVo(searchVo);
	}
	
}
