package com.spring.rest.security.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rest.security.context.UserContext;
import com.spring.rest.security.token.CustomToken;
import com.spring.rest.security.token.CustomTokenFactory;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

private static Logger logger = Logger.getLogger(CustomAuthenticationSuccessHandler.class);
	
	private final ObjectMapper mapper;
	
    private final CustomTokenFactory tokenFactory;
    
    @Autowired
    public CustomAuthenticationSuccessHandler(final CustomTokenFactory tokenFactory, final ObjectMapper mapper) {
      this.mapper = mapper;
      this.tokenFactory = tokenFactory;
    }

    //@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
    	logger.info("Enter into onAuthenticationSuccess");
        UserContext userContext = (UserContext) authentication.getPrincipal();
        
        CustomToken accessToken = tokenFactory.createAccessCustomToken(userContext);
        CustomToken refreshToken = tokenFactory.createRefreshToken(userContext);
        
        Map<String, String> tokenMap = new HashMap<String, String>();
        tokenMap.put("token", accessToken.getToken());
        tokenMap.put("refreshToken",refreshToken.getToken());

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), tokenMap);

        clearAuthenticationAttributes(request);
        logger.info("Exit from onAuthenticationSuccess");
    }

    /**
     * Removes temporary authentication-related data which may have been stored
     * in the session during the authentication process..
     * 
     */
    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}
