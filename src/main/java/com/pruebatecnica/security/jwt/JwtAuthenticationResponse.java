package com.pruebatecnica.security.jwt;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 5464134759311308208L;

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
