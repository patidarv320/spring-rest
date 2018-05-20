package com.spring.rest.security.token;

public interface TokenExtractor {

	public String extract(String payload);
}
