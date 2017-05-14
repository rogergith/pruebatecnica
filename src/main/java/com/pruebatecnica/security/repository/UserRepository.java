package com.pruebatecnica.security.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebatecnica.security.entity.User;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Serializable>{

	public User findByNombre(String nombre);
	
}
