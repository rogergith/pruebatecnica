package com.pruebatecnica.security.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="FormLogin", description="DTO para recibir el user/password")
public class FormLogin {

	@ApiModelProperty(value="nombre de usuario", name="username", required=true)
	private String username;
	
	@ApiModelProperty(value="password del usuario", name = "password", required=true)
	private String password;
	
	public FormLogin(){}
		
	public FormLogin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginForm [username=" + username + ", password=" + password + "]";
	}

	
}
