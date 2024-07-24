package com.demo.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

	@Autowired
	private JwtServiceImpl jwtServiceImpl;

	@PostMapping("/authenticate")

	public JwtResponse generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		return jwtServiceImpl.createJwtToken(jwtRequest);
	}
}
