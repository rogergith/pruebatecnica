package com.pruebatecnica.security.jwt;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="JwtAuthenticationResponse", description="Json Web Token")
public class JwtAuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 5464134759311308208L;

	@ApiModelProperty(value="token", name = "token", required=true)
	private String token;
	
	public JwtAuthenticationResponse(String token) {
		super();
		this.token = token;
	}


	public String getToken() {
		return token;
	}
	
	@Override
	public String toString() {
		return "JwtAuthenticationResponse [token=" + token + "]";
	}

	
}
