package com.pruebatecnica.model.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebatecnica.model.entity.User;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Serializable>{

	public User findByNombre(String nombre);
	
}
