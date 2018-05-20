package com.spring.rest.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ldap.dao.UserLdapDao;
import com.spring.rest.security.bean.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	public UserLdapDao userLdapDao;

	@Override
	public User loadUserbyUsername(String userName) {
		User userDetails = userLdapDao.loadUserbyUsername(userName);
		return userDetails;
	}


}
