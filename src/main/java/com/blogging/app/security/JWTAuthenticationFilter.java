package com.blogging.app.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFilter;


/*There are multiple layers of Authentication in an application.
If one fails then application moves to other layer.
Basically in authentication filter, at first the Converter Class is called.
it converts the request to type required by authentication manager.
now the AuthenticationManager is called.
there are different types of validations done by AuthenticationManager like JWT validation, Database validation etc.
If the authentication object type is not matched then request is redirected to next AuthenticationFilter.
If authentication object is matched as specified in AuthenticationManager then AuthenticationManager will do the Authentication.
If Authentication is success, then the next AuthenticationFilters are skipped and authentication is treated success.
We in this project have selected JWTAuthentication. so we have added dependencies and configured JWTAuthentication class.

Flow of Authentication.
AuthenticationFilter() -> AuthenticationConverter() -> AuthenticationManager() -> Authenticate()-> success
*/
public class JWTAuthenticationFilter extends AuthenticationFilter {

    public JWTAuthenticationFilter(JWTAuthenticationManager jwtAuthenticationManager) {
        super(jwtAuthenticationManager, new JWTAuthenticationConverter());

        this.setSuccessHandler(((request, response, authentication) ->
                SecurityContextHolder.getContext().setAuthentication(authentication)));
    }
}
