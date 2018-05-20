package com.spring.ldap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
@PropertySource("classpath:conf/ldap.properties")
public class LdapConfig {

	 @Autowired
	 private Environment env;
	 
	 private LdapContextSource getContextSource(){
		 LdapContextSource ldapContext =new LdapContextSource();
		 ldapContext.setUrl(env.getProperty("ldap.url"));
		 ldapContext.setBase(env.getProperty("ldap.base"));
		 ldapContext.setUserDn(env.getProperty("ldap.username"));
		 ldapContext.setPassword(env.getProperty("ldap.password"));
		 ldapContext.setPooled(true);
		 ldapContext.afterPropertiesSet();
		 return ldapContext;
	 }
	 
	 @Bean
	 public LdapTemplate getLdapTemplate(){
		 LdapTemplate ldapTemplate = new LdapTemplate(getContextSource());
		 return ldapTemplate;
	 }
	
}
