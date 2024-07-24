package com.demo.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.main.entity.User;
import com.demo.main.service.AdminService;

import jakarta.annotation.PostConstruct;

@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;

	@PostConstruct
	public void addRoles() {
		adminService.addRoles();
	}

	@PostMapping("/addAdmin")
	public ResponseEntity<User> addAdmin(@RequestBody User user) throws Exception {
		User addUser = adminService.addAdmin(user);
		return new ResponseEntity<>(addUser, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('Admin')")
	@PostMapping("/addCustomer")
	public ResponseEntity<User> addCustomer(@RequestBody User user) throws Exception {
		User addCustomer = adminService.addCustomer(user);
		return new ResponseEntity<>(addCustomer, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('Admin')")
	@PutMapping("/updateCustomer")
	public ResponseEntity<User> updateCustomer(@RequestParam String email, @RequestBody User user) throws Exception {
		User updateCustomer = adminService.updateCustomer(email, user);
		return new ResponseEntity<>(updateCustomer, HttpStatus.ACCEPTED);

	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers() throws Exception {
		List<User> getAllUsers = adminService.getAllUsers();
		return new ResponseEntity<>(getAllUsers, HttpStatus.ACCEPTED);

	}

}
