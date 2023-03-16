package com.toutsos.ssmultipleproviders.config.authentication;

import lombok.*;
import org.springframework.security.core.*;

import java.util.*;

@RequiredArgsConstructor
public class ApiKeyAuthenticationToken implements Authentication {

    private final String key;
    private boolean authenticated;

    public String getKey() {
        return key;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return null;
    }
}
