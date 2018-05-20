package com.spring.rest.security.service;

import com.spring.rest.security.bean.User;

public interface UserDetailsService {

	public User loadUserbyUsername(String userName);
	
}
