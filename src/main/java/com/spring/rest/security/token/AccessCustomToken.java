package com.spring.rest.security.token;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.jsonwebtoken.Claims;

public class AccessCustomToken implements CustomToken {
	
		private final String rawToken;
	    @JsonIgnore private Claims claims;

	    protected AccessCustomToken(final String token, Claims claims) {
	        this.rawToken = token;
	        this.claims = claims;
	    }

	    public String getToken() {
	        return this.rawToken;
	    }

	    public Claims getClaims() {
	        return claims;
	    }
}
