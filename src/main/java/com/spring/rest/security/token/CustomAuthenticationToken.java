package com.spring.rest.security.token;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.spring.rest.security.context.UserContext;

public class CustomAuthenticationToken extends AbstractAuthenticationToken {
	
	private static final long serialVersionUID = 4435363620354806455L;
	
	private RawAccessCustomToken rawAccessToken;
    private UserContext userContext;

    public CustomAuthenticationToken(RawAccessCustomToken unsafeToken) {
        super(null);
        this.rawAccessToken = unsafeToken;
        this.setAuthenticated(false);
    }

    public CustomAuthenticationToken(UserContext userContext, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.eraseCredentials();
        this.userContext = userContext;
        super.setAuthenticated(true);
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    //@Override
    public Object getCredentials() {
        return rawAccessToken;
    }

    //@Override
    public Object getPrincipal() {
        return this.userContext;
    }

    @Override
    public void eraseCredentials() {        
        super.eraseCredentials();
        this.rawAccessToken = null;
    }
}
