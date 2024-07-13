package com.blogging.app.security;

import com.blogging.app.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    public WebSecurityConfig(JWTAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    //We have bypass for create and login user. since these two will generate tokens.
    //which will be used by others for validation.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest.
                requestMatchers(HttpMethod.POST,"/users/create","users/login").permitAll().
                anyRequest().authenticated());

        http.addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class);

        return http.build();
    }
}
