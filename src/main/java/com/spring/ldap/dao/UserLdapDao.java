package com.spring.ldap.dao;

import com.spring.rest.security.bean.User;

public interface UserLdapDao {

	public User loadUserbyUsername(String username);
}
