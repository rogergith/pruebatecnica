package com.pruebatecnica.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = 6533852767168950636L;

	@Id
	@Column(name="id_role")
	private int id_role;
	
	@Column(name="rol")
	private String rol;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private List<User> users;
	
	public Role(){}
	
	

	public Role(int id_role, String rol, String descripcion, List<User> users) {
		super();
		this.id_role = id_role;
		this.rol = rol;
		this.descripcion = descripcion;
		this.users = users;
	}



	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [id_role=" + id_role + ", rol=" + rol + ", descripcion=" + descripcion + ", users=" + users + "]";
	}
	
	
}
