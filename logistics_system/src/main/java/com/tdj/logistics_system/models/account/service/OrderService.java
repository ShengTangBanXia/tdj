package com.tdj.logistics_system.models.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.tdj.logistics_system.models.account.entity.Address;
import com.tdj.logistics_system.models.account.entity.Order;
import com.tdj.logistics_system.models.common.vo.Result;
import com.tdj.logistics_system.models.common.vo.SearchVo;

public interface OrderService {

	PageInfo<Order> getOrdersBySearchVo (SearchVo searchVo);
	
	List <Address> getAddressByPid(int pid);
	
	Result<Order> addOrder(Order order);
	
	Result <Object> deleteOrder(String orderNum);
	
	Order orderSlecet (String orderNum);
	
	Result <Order> orderUpdate (Order order);
}
