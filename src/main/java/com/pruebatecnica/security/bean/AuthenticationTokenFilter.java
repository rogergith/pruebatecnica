package com.pruebatecnica.security.bean;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pruebatecnica.security.component.JwtTokenUtil;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	@Qualifier("UserUserDetailServiceImpl")
	public UserDetailsService userDetailsService;
	
	@Autowired
	@Qualifier("JwtTokenUtil")
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {
	
		String authToken = req.getHeader("Authorization");
		String username = jwtTokenUtil.getUsernameFromToken(authToken);
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			if(jwtTokenUtil.validateToken(authToken, userDetails)){
				
				UsernamePasswordAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
		}	
		
		chain.doFilter(req, res);
		
	}

	
	
}
