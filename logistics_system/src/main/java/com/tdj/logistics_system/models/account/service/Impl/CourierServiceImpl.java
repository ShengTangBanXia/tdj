package com.tdj.logistics_system.models.account.service.Impl;

import java.util.Collections;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdj.logistics_system.models.account.dao.CourierDao;
import com.tdj.logistics_system.models.account.entity.Courier;
import com.tdj.logistics_system.models.account.service.CourierService;
import com.tdj.logistics_system.models.common.vo.Result;
import com.tdj.logistics_system.models.common.vo.Result.resultStatus;
import com.tdj.logistics_system.models.common.vo.SearchVo;

@Service
public class CourierServiceImpl implements CourierService {

	private final static Logger LOGGER = LoggerFactory.getLogger(CourierServiceImpl.class);

	@Autowired
	private CourierDao courierDao;
	
	@Override
	public Courier getCourierByCourierName(String courierName) {
		return courierDao.getCourierByCourierName(courierName);
	}
	@Override
	public PageInfo<Courier> getCouriersBySearchVo(SearchVo searchVo) {
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo<>(
				Optional.ofNullable(courierDao.getCouriersBySearchVo(searchVo)).orElse(Collections.emptyList()));
	}

	@Override
	public Courier getCourierByCourierId(int courierId) {
		return courierDao.getCourierByCourierId(courierId);
	}

	@Override
	@Transactional
	public Result<Courier> edit(Courier courier) {
		Courier courierTemp = courierDao.getCourierByCourierName(courier.getCourierName());
		if (courierTemp != null && courierTemp.getCourierId() != courier.getCourierId()) {
			return new Result<Courier>(resultStatus.FAILED.status, "courier name is repeat.");
		}
		if (courier.getCourierId() > 0) {
			courierDao.updatecourier(courier);
		} else {
			courierDao.insertCourier(courier);
		}
		return new Result<Courier>(resultStatus.SUCCESS.status, "edit success.", courier);
	}
	@Override
	public Result<Object> deleteCourierByCourierId(int courierId) {
		courierDao.deleteCourierByCourierId(courierId);
		return new Result<Object>(resultStatus.SUCCESS.status, "Delete success.");
	}

}