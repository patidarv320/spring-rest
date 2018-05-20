package com.spring.rest.security.bean;

public class UserRole {

	 private Role role;
	    private int id;

	    public Role getRole() {
	        return role;
	    }
	    
	    public void setRole(Role role){
	    	this.role = role;
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}  
}
