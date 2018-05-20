package com.spring.rest.exception;

public class RequestValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5195869602102099766L;
	
	private String message;
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}	

	public RequestValidationException(){
		super();
	}
	
	public RequestValidationException(String message){
		super(message);
		this.message = message;
	}
	
	public RequestValidationException(Throwable th){
		super(th);
	}
}
