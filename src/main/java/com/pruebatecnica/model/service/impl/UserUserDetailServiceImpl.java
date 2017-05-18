package com.pruebatecnica.model.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pruebatecnica.model.entity.Role;
import com.pruebatecnica.model.entity.User;
import com.pruebatecnica.model.repository.UserRepository;

@Service("UserUserDetailServiceImpl")
public class UserUserDetailServiceImpl implements UserDetailsService, Serializable {

	private static final long serialVersionUID = 1121635318045048978L;

	@Autowired
	@Qualifier("UserRepository")
	private UserRepository sr;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = sr.findByNombre(username);
		
		List<GrantedAuthority> authorities = buildAuthorities(user.getRoles()); 
		
		return buildUser(user, authorities);
	
	}

	private UserDetails buildUser(User user, List<GrantedAuthority> authorities) {

		return new org.springframework.security.core.userdetails.User(user.getNombre(), user.getPassword(), true, true, true, true, authorities);
	
	}

	private List<GrantedAuthority> buildAuthorities(List<Role> roles) {
		
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		
		for(Role rol : roles){
			auths.add(new SimpleGrantedAuthority(rol.getRol()));
		}
		
		
		return new ArrayList<GrantedAuthority>(auths);
	}

	
	
}
