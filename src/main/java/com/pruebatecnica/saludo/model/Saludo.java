package com.pruebatecnica.saludo.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

public class Saludo implements Serializable {

	private static final long serialVersionUID = 6555413761883201274L;

	private String saludo;
	
	public Saludo(){}

	public Saludo(String saludo) {
		super();
		this.saludo = saludo;
	}

	public String getSaludo() {
		return saludo;
	}

	public void setSaludo(String saludo) {
		this.saludo = saludo;
	}

	@Override
	public String toString() {
		return "Saludo [saludo=" + saludo + "]";
	}

	
	
}
