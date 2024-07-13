package com.blogging.app.security;

import com.blogging.app.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JWTAuthenticationManager implements AuthenticationManager {
    private final JWTService jwtService;
    private final UserService userService;

    public  JWTAuthenticationManager(JWTService jwtService, UserService userService){
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof JWTAuthentication){

            var jwtAuthentication = (JWTAuthentication) authentication;
            var jwt = jwtAuthentication.getCredentials();
            var userId = jwtService.getSubjectUserId(jwt);

            jwtAuthentication.userEntity = userService.getById(userId);
            jwtAuthentication.setAuthenticated(true);

            return jwtAuthentication;
        }
        else
            throw new IllegalAccessError("Cannot authenticate with non-JWT authentication");
    }
}
