package com.tdj.account.models.account.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tdj.account.models.account.entity.City;
import com.tdj.account.models.account.service.Impl.SiteFeignClientFallBack;

@FeignClient(value = "CLIENT-SITE", fallback = SiteFeignClientFallBack.class)
public interface SiteFeignClient {

	@RequestMapping("/api/cities/{countryId}")
	List<City> getCitiesByCountryId(@PathVariable int countryId);
	
}
