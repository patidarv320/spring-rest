package com.spring.rest.security.util;

public enum Scopes {
	REFRESH_TOKEN;
	   public String authority() {
	        return "ROLE_" + this.name();
	    }
}
