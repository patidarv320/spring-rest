package com.spring.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.ldap.config.LdapConfig;
import com.spring.rest.security.config.CustomTokenSetting;
import com.spring.rest.security.config.SecurityConfig;

@EnableWebMvc
@Configuration
@Import(value={LdapConfig.class,SecurityConfig.class})
@ComponentScan(basePackages={"com.spring.rest","com.spring.ldap"})
@PropertySource(value = {"classpath:conf/tokensettings.properties"})
public class AppConfig extends WebMvcConfigurationSupport{
	@Autowired
	private Environment env;
	
	@Bean
	public ObjectMapper objectMapper(){
		ObjectMapper objectMapper =new ObjectMapper(); 
		return objectMapper;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Bean
	public CustomTokenSetting customTokenSetting(){
		CustomTokenSetting customTokenSetting = new CustomTokenSetting();
		customTokenSetting.setRefreshTokenExpTime(Integer.valueOf(env.getProperty("refreshTokenExpTime")));
		customTokenSetting.setTokenExpirationTime(Integer.valueOf(env.getProperty("tokenExpirationTime")));
		customTokenSetting.setTokenIssuer(env.getProperty("tokenIssuer"));
		customTokenSetting.setTokenSigningKey(env.getProperty("tokenSigningKey"));
		return customTokenSetting;
	}
}
