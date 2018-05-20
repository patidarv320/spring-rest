package com.spring.rest.security.service;

public interface TokenVerfierService {
	
	public boolean verify(String jti);

}
