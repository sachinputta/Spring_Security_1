package com.demo.main.serviceImplementation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.main.entity.Role;
import com.demo.main.entity.User;
import com.demo.main.repository.RoleRepository;
import com.demo.main.repository.UserRepository;
import com.demo.main.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	@Override
	public String addRoles() {
		Role adminRole = new Role();
		adminRole.setRolename("Admin");
		roleRepository.save(adminRole);

		Role userRole = new Role();
		userRole.setRolename("Customer");
		roleRepository.save(userRole);

		return "Success";
	}

	@Override
	public User addAdmin(User user) throws Exception {
		Role role = roleRepository.findById("Admin").orElseThrow(() -> new Exception("Role not found...!!"));
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		String encodedPassword = getEncodedPassword(user.getPassword());
		user.setPassword(encodedPassword);
		return userRepository.save(user);
	}

	@Override
	public User addCustomer(User user) throws Exception {
		Role role = roleRepository.findById("Customer").orElseThrow(() -> new Exception("Role not found...!!"));
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		String encodedPassword = getEncodedPassword(user.getPassword());
		user.setPassword(encodedPassword);
		return userRepository.save(user);
	}

	@Override
	public User updateCustomer(String email, User user) throws Exception {
		User user2 = userRepository.findById(email).orElseThrow(() -> new Exception("Email not found...!!"));

		String encodedPassword = getEncodedPassword(user.getPassword());
		user2.setPassword(encodedPassword);
		return userRepository.save(user2);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> findAll = userRepository.findAll();
		return findAll;
	}

}
