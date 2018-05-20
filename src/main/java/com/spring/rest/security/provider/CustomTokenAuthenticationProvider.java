package com.spring.rest.security.provider;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.spring.rest.security.config.CustomTokenSetting;
import com.spring.rest.security.context.UserContext;
import com.spring.rest.security.token.CustomAuthenticationToken;
import com.spring.rest.security.token.RawAccessCustomToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Component("customTokenAuthProvider")
public class CustomTokenAuthenticationProvider implements AuthenticationProvider{
	private static Logger logger = Logger.getLogger(CustomTokenAuthenticationProvider.class);

	private CustomTokenSetting customTokenSetting;
	
	@Autowired
    public CustomTokenAuthenticationProvider(CustomTokenSetting customTokenSetting) {
        this.customTokenSetting = customTokenSetting;
    }

    //@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	logger.info("Enter into authenticate");
    	RawAccessCustomToken rawAccessToken = (RawAccessCustomToken) authentication.getCredentials();

        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(customTokenSetting.getTokenSigningKey());
        String subject = jwsClaims.getBody().getSubject();
        List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
        List<GrantedAuthority> authorities = scopes.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
        
        UserContext context = UserContext.create(subject, authorities);
        logger.info("Exit from authenticate");
        return new CustomAuthenticationToken(context, context.getAuthorities());
    }

    //@Override
    public boolean supports(Class<?> authentication) {
        return (CustomAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
