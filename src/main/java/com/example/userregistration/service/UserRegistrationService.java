package com.example.userregistration.service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userregistration.model.User;
import com.example.userregistration.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserRegistrationService {

	@Autowired
	UserRepository userRepository;

	public User createUser(Map<String, String> req ) {

		User user = new User();
		user.setFirstname(req.get("firstname"));
		user.setLastname(req.get("lastname"));
		user.setEmail(req.get("email"));
		user.setReg_date(new Date(System.currentTimeMillis()));

		return userRepository.save(user);
	}

	public User updateUser(String id, Map<String, String> body) {
		int userId = Integer.parseInt(id);
		log.trace("userId ::::"+userId);
		User user = null;
		Optional<User> users = userRepository.findById(userId);
		if(users.isPresent()) {
			user  = users.get();
			user.setFirstname(body.get("firstname"));
			user.setLastname(body.get("lastname"));
			user.setEmail(body.get("email"));         
		}
		return userRepository.save(user);
	}

}
