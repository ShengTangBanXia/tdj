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
import com.tdj.logistics_system.models.account.entity.Courier;
import com.tdj.logistics_system.models.account.service.CourierService;
import com.tdj.logistics_system.models.common.vo.Result;
import com.tdj.logistics_system.models.common.vo.SearchVo;

@RestController
@RequestMapping("/api")
public class CourierController {

	@Autowired
	private CourierService courierService;

	/**
	 * 127.0.0.1/api/courier
	 */
	@PostMapping(value = "/courier", consumes = "application/json")
	public Result<Courier> insertCourier(@RequestBody Courier courier) {
		return courierService.edit(courier);
	}
	@PostMapping(value = "/couriers", consumes = "application/json")
	public PageInfo<Courier> getCouriersBySearchVo(@RequestBody SearchVo searchVo) {
		return courierService.getCouriersBySearchVo(searchVo);
	}

	/**
	 * 127.0.0.1/api/courier/3
	 */
	@RequestMapping("/courier/{courierId}")
	public Courier getCourierByCourierId(@PathVariable int courierId) {
		return courierService.getCourierByCourierId(courierId);
	}
	
	/**
	 * 127.0.0.1/api/courier
	 */
	@DeleteMapping("/courier/{courierId}")
	public Result<Object> deleteCourierByCourierId(@PathVariable int courierId) {
		return courierService.deleteCourierByCourierId(courierId);
	}
	
	@PutMapping(value = "/courier",consumes = "application/json")
	public Result<Courier> updateCourier(@RequestBody Courier courier){
		return courierService.edit(courier);
	}
}
