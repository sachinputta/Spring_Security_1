package com.demo.main.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.main.entity.User;
import com.demo.main.repository.UserRepository;
import com.demo.main.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

//	@Override
//	public User addUser(User user) {
//		String password = user.getPassword();
//		String encodedPassword = getEncodedPassword(password);
//		user.setPassword(encodedPassword);
//		return userRepository.save(user);
//	}

	@Override
	public User getUser(String email) throws Exception {
		User user = userRepository.findById(email).orElseThrow(()-> new Exception("Email not found...!!!"));
		return user;
	}
	

}
