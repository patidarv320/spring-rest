package com.spring.rest.security.provider;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring.rest.security.bean.User;
import com.spring.rest.security.context.UserContext;
import com.spring.rest.security.service.UserDetailsService;

@Component("customAuthProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider{

	
	@Autowired
	private UserDetailsService userDetailsService;

	private static Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);
	private final BCryptPasswordEncoder encoder;


    @Autowired
    public CustomAuthenticationProvider(final UserDetailsService userDetailsService, final BCryptPasswordEncoder encoder) {
    	this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.info("Enter into authenticate");
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        User user = null;
	        try{
	        	user = userDetailsService.loadUserbyUsername(username);
	        }catch(Exception ex){
	        	ex.printStackTrace();
	        }
	        
        if (user == null) {
            throw new UsernameNotFoundException("Authentication Failed. Username or Password not valid.");
        }else if(!user.getPassword().equals(password.trim())){
        	throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());
       
        UserContext userContext = UserContext.create(username, authorities);
        logger.info("Exit from authenticate");
        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
