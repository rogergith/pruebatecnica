package com.pruebatecnica.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -2590142816259546878L;
	
	@Id
	@Column( name = "id_user")
	private int id_user;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "password")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "user_roles",
		
		joinColumns = {
			@JoinColumn(name = "user", referencedColumnName = "id_user")	
		},
		
		inverseJoinColumns = {
			@JoinColumn(name = "rol", referencedColumnName = "id_role")	
		}
	)
	private List<Role> roles;
	
	public User(){}

	public User(String nombre, String password, List<Role> roles) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.roles = roles;
	}



	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", nombre=" + nombre + ", password=" + password + ", roles=" + roles + "]";
	}
	
	

}








