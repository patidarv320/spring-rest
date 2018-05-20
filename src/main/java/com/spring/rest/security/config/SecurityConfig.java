package com.spring.rest.security.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rest.security.filter.CustomLoginProcessingFilter;
import com.spring.rest.security.filter.CustomTokenAuthenticationProcessingFilter;
import com.spring.rest.security.filter.SkipPathRequestMatcher;
import com.spring.rest.security.token.TokenExtractor;
import com.spring.rest.security.util.SecurityConstant;

@Configuration
@EnableWebSecurity
@ComponentScan("com.spring.rest.security")
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
    @Autowired 
    private ObjectMapper objectMapper;
    
    @Autowired 
    private AuthenticationSuccessHandler successHandler;
    
    @Autowired 
    private AuthenticationFailureHandler failureHandler;
    
    @Autowired
    @Qualifier("customAuthenticationEntryPoint")
    private AuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	@Qualifier("customTokenAuthProvider")
	private AuthenticationProvider customTokenAuthenticationProvider;
	
	@Autowired
	@Qualifier("customAuthProvider")
	private AuthenticationProvider customAuthenticationProvider;
	
	@Autowired
	private TokenExtractor tokenExtractor;
	
	@Autowired 
    private AuthenticationManager authenticationManager;
    
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.csrf().disable() // disabling this is, because doesn't needed in JWT token
		.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
		.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.authorizeRequests()
			.antMatchers(SecurityConstant.FORM_BASED_LOGIN_ENTRY_POINT,
					SecurityConstant.TOKEN_REFRESH_ENTRY_POINT,
					SecurityConstant.TOKEN_VALIDATE_ENTRY_POINT).permitAll()
		.and()
			.authorizeRequests()
			.anyRequest().authenticated()
		.and()
			.addFilterBefore(buildCustomLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
			.addFilterAfter(buildTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
		auth.authenticationProvider(customTokenAuthenticationProvider);
	}
	
	 protected CustomLoginProcessingFilter buildCustomLoginProcessingFilter() throws Exception {
        CustomLoginProcessingFilter filter = new CustomLoginProcessingFilter(SecurityConstant.FORM_BASED_LOGIN_ENTRY_POINT, successHandler, failureHandler, objectMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
	  }
	 
	 protected CustomTokenAuthenticationProcessingFilter buildTokenAuthenticationProcessingFilter() throws Exception {
	        List<String> pathsToSkip = Arrays.asList(SecurityConstant.TOKEN_REFRESH_ENTRY_POINT,
	        		SecurityConstant.FORM_BASED_LOGIN_ENTRY_POINT);
	        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, 
	        		SecurityConstant.TOKEN_BASED_AUTH_ENTRY_POINT);
	        CustomTokenAuthenticationProcessingFilter filter 
	            = new CustomTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
	       filter.setAuthenticationManager(this.authenticationManager);
	        return filter;
	    } 
}
