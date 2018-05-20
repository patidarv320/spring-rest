package com.spring.rest.security.service;

import org.springframework.stereotype.Service;

@Service
public class TokenVerfierServiceImpl implements TokenVerfierService{

	public boolean verify(String jti){
		return true;
	}
}
