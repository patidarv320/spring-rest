package com.spring.rest.controller.helper;

import org.springframework.validation.BindingResult;

import com.spring.rest.exception.RequestValidationException;

public class ServiceHelper<T>{
	
	public boolean validateRequest(BindingResult bindingResult, T t) throws RequestValidationException{
		if(bindingResult.hasErrors()){
			throw new RequestValidationException(bindingResult.getFieldError().getDefaultMessage());
		}
		return true;
	}
	
}
