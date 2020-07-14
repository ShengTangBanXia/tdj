package com.tdj.logistics_system.models.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.tdj.logistics_system.models.account.entity.Address;
import com.tdj.logistics_system.models.account.entity.Order;
import com.tdj.logistics_system.models.account.service.OrderService;
import com.tdj.logistics_system.models.common.vo.Result;
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

	@PostMapping(value = "/address/{pid}", consumes = "application/json")
	@ResponseBody
	public List<Address> getAddressByPid(@PathVariable int pid) {
		
		List<Address> address = orderService.getAddressByPid(pid);
		return address;
	}
	
	/**
	 * 127.0.0.1/order/add-------------->post
	 */
	@PostMapping(value = "/add", consumes = "application/json")
	@ResponseBody
	public Result<Order> addOrder(@RequestBody Order order) {
		System.err.println(order);
		
		return orderService.addOrder(order);
	}
	
	/**
	 * 127.0.0.1/order/delete-------------->post
	 */
	@PostMapping("/delete")
	@ResponseBody
	public Result<Object> deleteOrder(String orderNum) {
		
		return orderService.deleteOrder(orderNum);
	}
	
	@RequestMapping("/select")
	@ResponseBody
	public Order orderSlecet(String orderNum) {
		
		return orderService.orderSlecet(orderNum);
	}
	
	@PutMapping(value = "/update", consumes = "application/json")
	@ResponseBody
	public Result<Order> orderUpdate(@RequestBody Order order) {
		
		return orderService.orderUpdate(order);
	}
	
}
