package com.tdj.account.models.account.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tdj.account.models.account.dao.UserDao;
import com.tdj.account.models.account.entity.City;
import com.tdj.account.models.account.entity.User;
import com.tdj.account.models.account.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public User getUserByUserId(int userId) {

		User user = userDao.getUserByUserId(userId);
		List<City> cities = restTemplate.getForObject("http://CLIENT-SITE/api/cities/{countryId}", List.class, 522);
		user.setCities(cities);
		
		return user;
	}

}
