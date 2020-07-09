package com.tdj.logistics_system.models.account.service;

import com.github.pagehelper.PageInfo;
import com.tdj.logistics_system.models.account.entity.Order;
import com.tdj.logistics_system.models.common.vo.SearchVo;

public interface OrderService {

	PageInfo<Order> getOrdersBySearchVo (SearchVo searchVo);
	
}
