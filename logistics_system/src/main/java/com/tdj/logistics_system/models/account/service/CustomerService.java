package com.tdj.logistics_system.models.account.service;

import com.github.pagehelper.PageInfo;
import com.tdj.logistics_system.models.account.entity.Customer;
import com.tdj.logistics_system.models.common.vo.Result;
import com.tdj.logistics_system.models.common.vo.SearchVo;

public interface CustomerService {

	PageInfo<Customer> getCustomerBySeachVo(SearchVo searchVo);
	
	Result<Customer> edit(Customer customer);

	Customer getCustomerBycustomerId(int customerId);

	Result<Object> deleteCustomerBycustomerId(int customerId);
}
