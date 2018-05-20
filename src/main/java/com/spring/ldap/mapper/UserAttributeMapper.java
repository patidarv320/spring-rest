package com.spring.ldap.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import com.spring.rest.security.bean.Role;
import com.spring.rest.security.bean.User;
import com.spring.rest.security.bean.UserRole;

public class UserAttributeMapper implements AttributesMapper<User> {

	@Override
	public User mapFromAttributes(Attributes attrs) throws NamingException {
		String username = (String)attrs.get("mail").get();
		Long userId = Long.valueOf((String)attrs.get("employeeNumber").get());
		byte[] bypePass = (byte[])attrs.get("userpassword").get();
		String role = (String)attrs.get("employeeType").get();
		String password = new String(bypePass);
		UserRole userRole = new UserRole();
		userRole.setId(1);
		userRole.setRole(Role.getRole(role));
		List<UserRole> list = new ArrayList<>();
		list.add(userRole);	
		User user = new User(userId,username,password,list);
        return user;
	}
}
