package com.tdj.account.models.account.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tdj.account.models.account.entity.City;

@FeignClient(value = "CLIENT-SITE")
public interface SiteFeignClient {

	@RequestMapping("/api/cities/{countryId}")
	List<City> getCitiesByCountryId(@PathVariable int countryId);
	
}
