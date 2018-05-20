package com.spring.rest.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.security.exception.CustomExpiredTokenException;
import com.spring.rest.security.token.CustomToken;
import com.spring.rest.security.token.RawAccessCustomToken;
import com.spring.rest.security.token.TokenExtractor;
import com.spring.rest.security.util.SecurityConstant;

@RestController
@RequestMapping(value="/api/validate/token")
public class ValidateTokenEndPoint {
	
	@Autowired
	private TokenExtractor tokenExtractor;

	@RequestMapping(method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
	public CustomToken validateToken(HttpServletRequest request, HttpServletResponse response)
		throws CustomExpiredTokenException ,BadCredentialsException {
		 String tokenPayload = request.getHeader(SecurityConstant.JWT_TOKEN_HEADER_PARAM);
	     RawAccessCustomToken token = new RawAccessCustomToken(tokenExtractor.extract(tokenPayload));
		return token;
	}
}
