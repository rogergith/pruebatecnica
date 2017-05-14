package com.pruebatecnica.security.component;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component("JwtAuthenticationEntryPoint")
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 3418727098487466226L;

	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res,
			AuthenticationException authException) throws IOException, ServletException {
		
		/*
		 
		 Esto se invoca cuando el usuario intenta acceder a un recurso REST seguro sin 
		 suministrar ninguna credencial. Deberíamos enviar una respuesta no autorizada 401 
		 porque no hay una página de inicio de sesión a la que redireccionar
		 
		 */
		
		res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "NO AUTORIZADO");
		
		
	}

}
