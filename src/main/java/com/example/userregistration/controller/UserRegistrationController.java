package com.example.userregistration.controller;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		user.setFirstname(req.get("firstname"));
		user.setLastname(req.get("lastname"));
		user.setEmail(req.get("email"));
		user.setReg_date(new Date(System.currentTimeMillis()));
	
		return userRepository.save(user);
	}
	
	
	@PostMapping("/user/{id}")
    public User update(@PathVariable String id, @RequestBody Map<String, String> body){
        int userId = Integer.parseInt(id);
        
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
