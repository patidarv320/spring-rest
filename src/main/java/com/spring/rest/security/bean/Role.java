package com.spring.rest.security.bean;


public enum Role{
	ADMIN, PREMIUM_MEMBER, MEMBER;
    
    public String authority() {
        return "ROLE_" + this.name();
    }
    
    public static Role getRole(String role){
    	if(role.equalsIgnoreCase(Role.ADMIN.name())){
    		return Role.ADMIN;
    	}
    	return Role.ADMIN;
    }
	
}
