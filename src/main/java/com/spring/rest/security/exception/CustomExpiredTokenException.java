package com.spring.rest.security.exception;

import org.springframework.security.core.AuthenticationException;

import com.spring.rest.security.token.CustomToken;

public class CustomExpiredTokenException extends AuthenticationException{

	private static final long serialVersionUID = -1744336174550215200L;
	
	private CustomToken token;

    public CustomExpiredTokenException(String msg) {
        super(msg);
    }

    public CustomExpiredTokenException(CustomToken token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public String token() {
        return this.token.getToken();
    }
}
