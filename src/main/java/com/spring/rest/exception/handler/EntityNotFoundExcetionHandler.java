package com.spring.rest.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.spring.rest.bean.ApiError;
import com.spring.rest.exception.EntityNotFoundException;

@ControllerAdvice
public class EntityNotFoundExcetionHandler {

	@ExceptionHandler(value=EntityNotFoundException.class)
	public ResponseEntity<ApiError> handleExceptions(Exception ex,HttpServletRequest request) {
		System.out.println("Exception Handler executed...");
        return handleExceptionInternal(ex);
	}
	
	private ResponseEntity<ApiError> handleExceptionInternal(Exception ex){
		ApiError apiError = new ApiError();
		apiError.setErrorMsg(ex.getMessage());
		apiError.setStatus("404");
		return new ResponseEntity<ApiError>(apiError,HttpStatus.NOT_FOUND);
	}

}
