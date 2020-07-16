package com.tdj.logistics_system.models.account.service;
import com.github.pagehelper.PageInfo;
import com.tdj.logistics_system.models.account.entity.Courier;
import com.tdj.logistics_system.models.common.vo.Result;
import com.tdj.logistics_system.models.common.vo.SearchVo;

public interface CourierService {
	Courier getCourierByCourierName(String courierName);
	
	PageInfo<Courier> getCouriersBySearchVo(SearchVo searchVo);
	
	Courier getCourierByCourierId(int courierId);
	
	Result<Object> deleteCourierByCourierId(int courierId);
	
	Result<Courier> edit(Courier courier);

}
