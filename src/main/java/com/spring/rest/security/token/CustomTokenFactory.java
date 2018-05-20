package com.spring.rest.security.token;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.rest.security.config.CustomTokenSetting;
import com.spring.rest.security.context.UserContext;
import com.spring.rest.security.util.Scopes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class CustomTokenFactory {

private static Logger logger = Logger.getLogger(CustomTokenFactory.class);
	
	private final CustomTokenSetting setting;

    @Autowired
    public CustomTokenFactory(CustomTokenSetting setting) {
        this.setting = setting;
    }

    /**
     * Factory method for issuing new JWT Tokens.
     * 
     * @param username
     * @param roles
     * @return
     */
    public CustomToken createAccessCustomToken(UserContext userContext) {
    	logger.info("Enter into createAccessCustomToken");
        if (StringUtils.isBlank(userContext.getUsername())) 
            throw new IllegalArgumentException("Cannot create Custom Token without username");

        if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty()) 
            throw new IllegalArgumentException("User doesn't have any privileges");

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put("scopes", userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

        DateTime currentTime = new DateTime();

        String token = Jwts.builder()
          .setClaims(claims)
          .setIssuer(setting.getTokenIssuer())
          .setIssuedAt(currentTime.toDate())
          .setExpiration(currentTime.plusMinutes(setting.getTokenExpirationTime()).toDate())
          .signWith(SignatureAlgorithm.HS512, setting.getTokenSigningKey())
        .compact();
        logger.info("Exit from createAccessCustomToken");
        return new AccessCustomToken(token, claims);
    }

    public CustomToken createRefreshToken(UserContext userContext) {
    	logger.info("Enter into createRefreshToken");
        if (StringUtils.isBlank(userContext.getUsername())) {
            throw new IllegalArgumentException("Cannot create Custom Token without username");
        }

        DateTime currentTime = new DateTime();

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put("scopes", Arrays.asList(Scopes.REFRESH_TOKEN.authority()));
        
        String token = Jwts.builder()
          .setClaims(claims)
          .setIssuer(setting.getTokenIssuer())
          .setId(UUID.randomUUID().toString())
          .setIssuedAt(currentTime.toDate())
          .setExpiration(currentTime.plusMinutes(setting.getRefreshTokenExpTime()).toDate())
          .signWith(SignatureAlgorithm.HS512, setting.getTokenSigningKey())
        .compact();
        logger.info("Exit from createRefreshToken");
        return new AccessCustomToken(token, claims);
    }
}
