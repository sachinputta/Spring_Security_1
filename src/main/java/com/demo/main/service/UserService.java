package com.demo.main.service;

import com.demo.main.entity.User;

public interface UserService {
//	public User addUser(User user);

	public User getUser(String email) throws Exception;	

}
