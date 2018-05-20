package com.spring.rest.dao;

import com.spring.rest.security.bean.User;

public interface UserDao {

	public User loadUserbyUsername(String username);
}
