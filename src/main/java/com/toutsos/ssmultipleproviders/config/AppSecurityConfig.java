package com.toutsos.ssmultipleproviders.config;

import com.toutsos.ssmultipleproviders.config.filters.*;
import com.toutsos.ssmultipleproviders.config.providers.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.www.*;

@Configuration
@EnableWebSecurity (debug = true)
public class AppSecurityConfig {

    @Value("${secret.key}")
    private String key;

    /**
     * Here we configure the default security filter chain
     * We implement basic login
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .anyRequest().authenticated();

        http
                .addFilterBefore(new ApiKeyFilter(key), BasicAuthenticationFilter.class);
       return http.build();
    }

    /**
     * We create an in memory user for basic auth
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("toutsos")
                .password("1234")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

}
