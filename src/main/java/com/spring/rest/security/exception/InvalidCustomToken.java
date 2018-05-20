package com.spring.rest.security.exception;

public class InvalidCustomToken extends Exception {

	private static final long serialVersionUID = -7696494959860901651L;
	
	private String message;
	
	public InvalidCustomToken(String message){
		super(message);
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
