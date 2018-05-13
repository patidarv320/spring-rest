package com.spring.rest.exception;

/*
 * @Author : Vikas Patidar
 * @Since  : 13-MAY-2018
 * @Description : this will throw when Employee object(s) not available.
 */
public class EmployeeObjectNotFoundException extends EntityNotFoundException{

	/**
	 * Generated Serial version uid.
	 */
	private String message;
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -6513368238959949175L;

	public EmployeeObjectNotFoundException() {
		super();
	}	
	
	public EmployeeObjectNotFoundException(String message) {
		super(message);
		this.message = message;
	}	
	
}
