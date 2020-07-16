package com.tdj.logistics_system.models.account.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdj.logistics_system.models.account.dao.CustomerDao;
import com.tdj.logistics_system.models.account.dao.CustomerRoleDao;
import com.tdj.logistics_system.models.account.entity.Customer;
import com.tdj.logistics_system.models.account.entity.Role;
import com.tdj.logistics_system.models.account.service.CustomerService;
import com.tdj.logistics_system.models.common.vo.Result;
import com.tdj.logistics_system.models.common.vo.Result.resultStatus;
import com.tdj.logistics_system.models.common.vo.SearchVo;
import com.tdj.logistics_system.utils.MD5Util;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
	private CustomerDao customerDao;
    
    @Autowired
	private CustomerRoleDao customerRoleDao;

	@Override
	public PageInfo<Customer> getCustomerBySeachVo(SearchVo searchVo) {
		PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
		return new PageInfo<>(Optional.ofNullable(customerDao.getCustomerBySearchVo(searchVo)).orElse(Collections.emptyList()));
	}

	@Override
	public Result<Customer> edit(Customer customer) {
		Customer customerTemp = customerDao.getCustomerByCustomerName(customer.getCustomerName());
		if (customerTemp != null && customerTemp.getCustomerId() != customer.getCustomerId()) {
			return new Result<Customer>(resultStatus.FAILED.status, "Customer name is repeat.");
		}
		if (customer.getCustomerId() > 0) {
			customerDao.updateCustomer(customer);
			customerRoleDao.deleteRolesByUserId(customer.getCustomerId());
			
		} else {
			customer.setPassword(MD5Util.getMD5(customer.getPassword()));
			customerDao.insertCustomer(customer);
		}
		List<Role> roles = customer.getRoles();
		if (roles != null && roles.size() > 0) {
			for (Role role : roles) {
				customerRoleDao.insertCustomerRole(customer.getCustomerId(), role.getRoleId());
			}
		}
		return new Result<Customer>(resultStatus.SUCCESS.status, "edit success.", customer);
	}

	@Override
	public Customer getCustomerBycustomerId(int customerId) {
		return customerDao.getCustomerBycustomerId(customerId);
	}

	@Override
	public Result<Object> deleteCustomerBycustomerId(int customerId) {
		customerDao.deleteCustomerBycustomerId(customerId);
		customerRoleDao.deleteRolesByUserId(customerId);
		return new Result<>(resultStatus.SUCCESS.status,"delete success.");
	}
	
}