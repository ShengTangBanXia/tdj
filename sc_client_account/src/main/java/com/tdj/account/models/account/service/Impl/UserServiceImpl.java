package com.tdj.account.models.account.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdj.account.models.account.dao.UserDao;
import com.tdj.account.models.account.entity.User;
import com.tdj.account.models.account.service.SiteFeignClient;
import com.tdj.account.models.account.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private SiteFeignClient siteFeignClient;
	
	@Override
	//@HystrixCommand(fallbackMethod = "getUserByUserIdFallback")
	public User getUserByUserId(int userId) {

		User user = userDao.getUserByUserId(userId);
		//List<City> cities = restTemplate.getForObject("http://CLIENT-SITE/api/cities/{countryId}", List.class, 522);
//		user.setCities(cities);
		user.setCities(siteFeignClient.getCitiesByCountryId(522));
		
		return user;
	}

//	public User getUserByUserIdFallback(int userId) {
//		
//		User user = userDao.getUserByUserId(userId);
//		user.setCities(new ArrayList<City>());
//		
//		return user;
//		
//	}
	
}
