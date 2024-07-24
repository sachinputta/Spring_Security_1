package com.demo.main.security;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.main.entity.User;
import com.demo.main.repository.UserRepository;



@Service
public class JwtServiceImpl implements UserDetailsService 
{
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager ;
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception
	{
		String email = jwtRequest.getEmail();
		String password=jwtRequest.getPassword();
		authenticate(email,password);
		
		UserDetails userDetails=loadUserByUsername(email);
		Optional<User>user = userRepository.findById(email);
		String token=jwtUtil.generateToken(userDetails);
		return new JwtResponse(token,user.get());
	}
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
	{
		User user=userRepository.findById(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email-Id not found.. !!"));
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),user.getAuthorities());
		
		
	}
	
	private void authenticate(String email, String password) throws Exception
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		}
		catch(DisabledException e)
		{
			throw new Exception("USER DISABLED",e);
		}
		catch(BadCredentialsException e)
		{
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
