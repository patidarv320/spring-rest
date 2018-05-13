package com.spring.rest.exception;

public class EntityNotFoundException extends Exception {

	/**
	 * serialVersionUID 
	 */
	private static final long serialVersionUID = -5401116485731141771L;
	
	private String message;
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}	

	public EntityNotFoundException(){
		super();
	}
	
	public EntityNotFoundException(String message){
		super(message);
		this.message = message;
	}
	
	public EntityNotFoundException(Throwable th){
		super(th);
	}
	
}
