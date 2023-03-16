package com.toutsos.ssmultipleproviders.config.managers;

import com.toutsos.ssmultipleproviders.config.providers.*;
import lombok.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;

@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final String key;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var provider = new ApiKeyProvider(key);
        if (provider.supports(authentication.getClass())){
            return provider.authenticate(authentication);
        }
        return authentication;
    }
}
