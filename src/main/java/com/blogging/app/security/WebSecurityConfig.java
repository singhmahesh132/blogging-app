package com.blogging.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest.
                requestMatchers(HttpMethod.GET,"/*").permitAll().
                requestMatchers(HttpMethod.POST,"/users/create","users/login").permitAll().
                requestMatchers("/h2-console/*").permitAll().
                requestMatchers(HttpMethod.GET,"/articles/*").permitAll().
                anyRequest().authenticated());
        return http.build();
    }
}
