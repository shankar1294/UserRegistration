package com.example.userregistration.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.userregistration.model.User;
import com.example.userregistration.repository.UserRepository;

@RestController
public class UserRegistrationController {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping(value = "/user" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public User createUser(@RequestBody Map<String, String> req ) {
		
		User user = new User();
		
		System.out.println("first :;"+req.get("firstname") +"last ;;"+req.get("lastname") +"email" +req.get("email") );
		user.setFirstname(req.get("firstname"));
		user.setLastname(req.get("lastname"));
		user.setEmail(req.get("email"));
		user.setReg_date(new Date(System.currentTimeMillis()));
	
		return userRepository.save(user);
	}
	
	@GetMapping("/test")
	public void testGet() {
		
	}

}
