 package com.demo.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.demo.main.entity.User;
import com.demo.main.service.UserService;

@RestController
public class UserController {

	@Autowired
	public UserService userService;
	
//	@PostMapping("/addUser")
//	public ResponseEntity<User> addUser(@RequestBody User user) {
//		User addUser = userService.addUser(user);
//		return new ResponseEntity<>(addUser,HttpStatus.OK);
//		
//	}
	
	@PreAuthorize("hasRole('Customer')")
	@GetMapping("/getUser/{email}")
	public  ResponseEntity<User> getUser(@PathVariable String email) throws Exception {
		User u1 = userService.getUser(email);
		return new ResponseEntity<>(u1,HttpStatus.OK);
		
	}
	
	
	
}
