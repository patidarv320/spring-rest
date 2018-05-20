package com.spring.rest.dao;

import java.util.ArrayList;
import java.util.List;

import com.spring.rest.security.bean.Role;
import com.spring.rest.security.bean.User;
import com.spring.rest.security.bean.UserRole;

public class UserDaoImpl{

	public User loadUserbyUsername(String username) {
		
		UserRole userRole = new UserRole();
		userRole.setId(1);
		userRole.setRole(Role.ADMIN);
		
		List<UserRole> list = new ArrayList<>();
		list.add(userRole);		
		User user = new User((long)1234,"vikas","1234",list);    
        return user;
		
	}

}
