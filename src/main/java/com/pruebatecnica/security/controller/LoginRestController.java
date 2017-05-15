package com.pruebatecnica.security.controller;

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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/security")
public class LoginRestController {

	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	@Qualifier("UserUserDetailServiceImpl")
	private UserDetailsService userDetailsService;
	
	@Autowired
	@Qualifier("JwtTokenUtil")
	private JwtTokenUtil jwtTokenUtil;
	
	@ApiOperation(value="LOGIN", nickname="SUPER LOGIN", produces="Json Web Token")
	@ApiResponses(
			value = { 
				@ApiResponse(code = 200, message = "exito", response = JwtAuthenticationResponse.class),
				@ApiResponse(code = 403, message = "NO AUTORIZADO")
			}
	)
	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody FormLogin login){

		String token = null;

		//Realizar la seguridad
		UsernamePasswordAuthenticationToken authToken =
				new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
		
		Authentication authentication = authManager.authenticate(authToken);
		
		
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//GENERAR TOKEN
		UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUsername());
		
		token = jwtTokenUtil.generateToken(userDetails);
		
		
		return new ResponseEntity<JwtAuthenticationResponse>(new JwtAuthenticationResponse(token), HttpStatus.OK);
	
	
	}
	
}
