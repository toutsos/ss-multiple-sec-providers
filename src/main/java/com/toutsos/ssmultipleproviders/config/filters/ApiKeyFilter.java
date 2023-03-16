package com.toutsos.ssmultipleproviders.config.filters;

import com.toutsos.ssmultipleproviders.config.authentication.*;
import com.toutsos.ssmultipleproviders.config.managers.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.web.filter.*;

import java.io.*;

@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

    private final String key;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var requestKey = request.getHeader("x-api-key");
        if (requestKey == null || "null".equals(requestKey)) {
            filterChain.doFilter(request, response);
        }

        /**
         * We create a new authentication with authenticated = false
         */
        var auth = new ApiKeyAuthenticationToken(requestKey);

        var manager = new CustomAuthenticationManager(key);

        try {
           var a =  manager.authenticate(auth);
           if (a.isAuthenticated()){
               SecurityContextHolder.getContext().setAuthentication(a);
               filterChain.doFilter(request,response);
           }else{
               response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           }
        }catch (AuthenticationException a){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }


    }
}
