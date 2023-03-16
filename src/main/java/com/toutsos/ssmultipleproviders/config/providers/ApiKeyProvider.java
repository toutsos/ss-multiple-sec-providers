package com.toutsos.ssmultipleproviders.config.providers;

import com.toutsos.ssmultipleproviders.config.authentication.*;
import lombok.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;

@AllArgsConstructor
public class ApiKeyProvider implements AuthenticationProvider {

    private final String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiKeyAuthenticationToken token = (ApiKeyAuthenticationToken) authentication;
        String requestKey = token.getKey();
        if (requestKey.equals(key)){
            token.setAuthenticated(true);
        }
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(ApiKeyAuthenticationToken.class);
    }
}
