package com.example.userregistration.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.userregistration.model.User;
import com.example.userregistration.service.UserRegistrationService;
import com.example.userregistration.util.Uservalidation;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class UserRegistrationController {
	
	@Autowired
	UserRegistrationService userRegistrationService;
	
	@PostMapping(value = "/user" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public User createUser(@RequestBody Map<String, String> req ) throws Exception {
		
		log.trace("inside create user ....");
		Uservalidation uservalidation = new Uservalidation();
		uservalidation.validateRequest(req);
		
		return userRegistrationService.createUser(req);
	}
	
	@Cacheable(value = "users", key = "#id")
	@PostMapping("/user-update/{id}")
    public User update(@PathVariable String id, @RequestBody Map<String, String> body){
       
		return userRegistrationService.updateUser(id, body);
    }
	
	@GetMapping("/healthcheck")
	public String getAllProduct() throws Exception{		
		return "success";	
	}

}
