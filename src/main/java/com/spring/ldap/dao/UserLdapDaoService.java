package com.spring.ldap.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Repository;

import com.spring.ldap.mapper.UserAttributeMapper;
import com.spring.rest.security.bean.User;

@Repository
public class UserLdapDaoService implements UserLdapDao{

	private static final Logger LOGGER = Logger.getLogger(UserLdapDaoService.class);
	
	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Override
	public User loadUserbyUsername(String username) {
		LOGGER.info("Enter into loadUserbyUsername."+username);
		LdapQuery query = LdapQueryBuilder.query()
		         //.base("ou=people,dc=maxcrc,dc=com")
		         .where("mail").is(username);
		LOGGER.info("Exit from loadUserbyUsername.");
		return ldapTemplate.search(query,new UserAttributeMapper()).get(0);
	}
}
