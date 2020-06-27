package com.tdj.account.models.account.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tdj.account.models.account.entity.City;
import com.tdj.account.models.account.service.SiteFeignClient;

@Component
public class SiteFeignClientFallBack implements SiteFeignClient {

	@Override
	public List<City> getCitiesByCountryId(int countryId) {

		return new ArrayList<City>();

	}

}
