package com.pruebatecnica.security.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.security.component.JwtTokenUtil;
import com.pruebatecnica.security.controller.dto.FormLogin;
import com.pruebatecnica.security.jwt.JwtAuthenticationResponse;

@RestController
@RequestMapping("/security")
public class LoginSecurityRestController {
	
	private static final Log LOG = LogFactory.getLog(LoginSecurityRestController.class);
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	@Qualifier("UserUserDetailServiceImpl")
	private UserDetailsService userDetailsService; 
	
	@Autowired
	@Qualifier("JwtTokenUtil")
	private JwtTokenUtil jwtTokenUtil;
	
	/**
	 * http://localhost:8980/security/login
	 */
	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody FormLogin login){
		
		String token;
		
		//Realizar la seguridad
		UsernamePasswordAuthenticationToken authToken =
				new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
		
		Authentication authentication = authManager.authenticate(authToken);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUsername());
		
		//generar token
		token = jwtTokenUtil.generateToken(userDetails);
		
		return new ResponseEntity<JwtAuthenticationResponse>(new JwtAuthenticationResponse(token), HttpStatus.OK);
		
	}
	
}
