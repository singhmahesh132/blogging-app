package com.blogging.app.security;

import com.blogging.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

public class JWTAuthenticationManager implements AuthenticationManager {

    @Autowired
    JWTService jwtService;

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof JWTAuthentication){

            var jwtAuthentication = (JWTAuthentication) authentication;
            var jwt = jwtAuthentication.getCredentials();
            var userId = jwtService.getSubjectUserId(jwt);

            jwtAuthentication.userEntity = userService.getById(userId);

            if(jwtAuthentication.userEntity.getId() == userId){
                SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
                jwtAuthentication.setAuthenticated(true);
            }

            return jwtAuthentication;
        }
        else
            throw new IllegalAccessError("Cannot authenticate with non-JWT authentication");
    }
}
