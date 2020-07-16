package com.tdj.logistics_system.models.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.tdj.logistics_system.models.account.entity.Customer;
import com.tdj.logistics_system.models.account.service.CustomerService;
import com.tdj.logistics_system.models.common.vo.Result;
import com.tdj.logistics_system.models.common.vo.SearchVo;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
	private CustomerService customerService;

	@PostMapping(value = "/customers", consumes = "application/json")
	public PageInfo<Customer> getCustomerBySeachVo(@RequestBody SearchVo searchVo) {
		return customerService.getCustomerBySeachVo(searchVo);
	}
	
	@PostMapping(value = "/customer", consumes = "application/json")
	public Result<Customer> insertUser(@RequestBody Customer customer) {
		return customerService.edit(customer);
	}
	
	@PutMapping(value = "/customer",consumes = "application/json")
	public Result<Customer> updateUser(@RequestBody Customer customer){
		return customerService.edit(customer);
	}
	
	@RequestMapping("/customer/{customerId}")
	public Customer getUserByUserId(@PathVariable int customerId) {
		return customerService.getCustomerBycustomerId(customerId);
	}
	
	@DeleteMapping("/customer/{customerId}")
	public Result<Object> deleteUserByUserId(@PathVariable int customerId) {
		return customerService.deleteCustomerBycustomerId(customerId);
	}
}
