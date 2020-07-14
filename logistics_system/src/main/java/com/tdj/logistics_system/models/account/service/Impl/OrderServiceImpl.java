package com.tdj.logistics_system.models.account.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdj.logistics_system.models.account.dao.OrderDao;
import com.tdj.logistics_system.models.account.entity.Address;
import com.tdj.logistics_system.models.account.entity.Order;
import com.tdj.logistics_system.models.account.service.OrderService;
import com.tdj.logistics_system.models.common.vo.Result;
import com.tdj.logistics_system.models.common.vo.Result.resultStatus;
import com.tdj.logistics_system.models.common.vo.SearchVo;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public PageInfo<Order> getOrdersBySearchVo(SearchVo searchVo) {
//		List <Order> orders = orderDao.getOrdersBySearchVo(searchVo);
//		System.err.println(orders);

		searchVo.initSearchVo();	//初始化分页
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());	//开启分页
		
		return new PageInfo<Order>(Optional.ofNullable(orderDao.getOrdersBySearchVo(searchVo))
				.orElse(Collections.emptyList()));
	}

	@Override
	public List<Address> getAddressByPid(int pid) {

		return orderDao.getAddressByPid(pid);
	}

	@Override
	@Transactional
	public Result<Order> addOrder(Order order) {
		
		//设置订单状态值
		order.setOrderStatus("已录入");
		
		orderDao.addOrder(order);
		String message = "add order success!!!";
		
		return new Result<Order> (resultStatus.SUCCESS.status, message, order);
	}

	@Override
	@Transactional
	public Result<Object> deleteOrder(String orderNum) {

		orderDao.deleteOrder(orderNum);
		
		return new Result <Object> (resultStatus.SUCCESS.status, "Delete Order Success!!!");
	}

	@Override
	public Order orderSlecet(String orderNum) {

		return orderDao.orderSlecet(orderNum);
	}

	@Override
	@Transactional
	public Result<Order> orderUpdate(Order order) {

		orderDao.orderUpdate(order);
		
		return new Result <Order> (resultStatus.SUCCESS.status, "Update Order Success!!!");
	}

}
