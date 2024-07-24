package com.demo.main.service;

import java.util.List;

import com.demo.main.entity.User;


public interface AdminService {
	
	public String addRoles();
	
	public User addAdmin(User user) throws Exception;
     
	public User addCustomer(User user) throws Exception;
	
	public User updateCustomer(String email, User user) throws Exception;
	
	public List<User> getAllUsers();
}
