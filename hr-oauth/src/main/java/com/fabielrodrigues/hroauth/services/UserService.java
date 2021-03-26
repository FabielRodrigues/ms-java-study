package com.fabielrodrigues.hroauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabielrodrigues.hroauth.entities.User;
import com.fabielrodrigues.hroauth.feignclients.UserFeignClient;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	
	@Autowired
	public UserFeignClient userFeignClient;
	
	public User findByEmail(String email) {
		User user = userFeignClient.findByEmail(email).getBody();
		if (user == null) {
			logger.error("Email no found" + email);
			throw new IllegalArgumentException("Email not found");
		}
		logger.info("Email found: " + email);
		return user;
	}
}
