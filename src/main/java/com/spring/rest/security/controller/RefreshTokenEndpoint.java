package com.spring.rest.security.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.security.bean.User;
import com.spring.rest.security.config.CustomTokenSetting;
import com.spring.rest.security.context.UserContext;
import com.spring.rest.security.exception.InvalidCustomToken;
import com.spring.rest.security.service.TokenVerfierService;
import com.spring.rest.security.service.UserDetailsService;
import com.spring.rest.security.token.CustomToken;
import com.spring.rest.security.token.CustomTokenFactory;
import com.spring.rest.security.token.RawAccessCustomToken;
import com.spring.rest.security.token.RefreshToken;
import com.spring.rest.security.token.TokenExtractor;
import com.spring.rest.security.util.SecurityConstant;


@RestController
@RequestMapping(value="/api/auth/token")
public class RefreshTokenEndpoint {
	
	private static Logger logger = Logger.getLogger(RefreshTokenEndpoint.class);
			
    @Autowired private CustomTokenFactory tokenFactory;
    @Autowired private CustomTokenSetting customTokenSetting;
    @Autowired private UserDetailsService userService;
    @Autowired private TokenVerfierService tokenVerifier;
    @Autowired private TokenExtractor tokenExtractor;
    
    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody CustomToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InvalidCustomToken {
    	logger.info("Enter into refreshToken");
        String tokenPayload = tokenExtractor.extract(request.getHeader(SecurityConstant.JWT_TOKEN_HEADER_PARAM));
        RawAccessCustomToken rawToken = new RawAccessCustomToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, customTokenSetting.getTokenSigningKey()).orElseThrow(() -> new InvalidCustomToken("Invalid Custom Token"));

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidCustomToken("Invalid Custome token");
        }

        String subject = refreshToken.getSubject();
        User user = userService.loadUserbyUsername(subject);
        
        if(user==null){
        	throw new UsernameNotFoundException("User doesn't found for refresh token");
        }

        if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getUsername(), authorities);
        logger.info("Exit from refreshToken");
        return tokenFactory.createAccessCustomToken(userContext);
    }
}

