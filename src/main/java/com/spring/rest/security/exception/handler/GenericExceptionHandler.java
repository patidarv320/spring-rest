package com.spring.rest.security.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.rest.bean.ApiError;
import com.spring.rest.exception.EntityNotFoundException;
import com.spring.rest.security.exception.CustomExpiredTokenException;

@ControllerAdvice
public class GenericExceptionHandler {

	@ExceptionHandler(value=BadCredentialsException.class)
	public ResponseEntity<ApiError> handleBadCredentialsException(Exception ex,HttpServletRequest request) {
		System.out.println("Exception Handler executed...");
        return handleExceptionInternal(ex,HttpStatus.UNAUTHORIZED);
	}
	
	private ResponseEntity<ApiError> handleExceptionInternal(Exception ex, HttpStatus httpStatus){
		ApiError apiError = new ApiError();
		apiError.setErrorMsg(ex.getMessage());
		apiError.setStatus(Integer.toString(httpStatus.value()));
		return new ResponseEntity<ApiError>(apiError,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=CustomExpiredTokenException.class)
	public ResponseEntity<ApiError> handleCustomExpiredTokenException(Exception ex,HttpServletRequest request) {
		System.out.println("Exception Handler executed...");
        return handleExceptionInternal(ex,HttpStatus.UNAUTHORIZED);
	}
}
