package com.spring.rest.security.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

public class AuthMethodNotSupportedException extends AuthenticationServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5239711793778547120L;

	public AuthMethodNotSupportedException(String message) {
		super(message);
		this.message = message;
	}
	
	private String message;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	
}
