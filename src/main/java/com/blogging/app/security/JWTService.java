package com.blogging.app.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    //This is the JWT secretKey required for JWT encryption and decryption.
    //TODO: Keep this in separate properties file
    private static final String JWT_KEY = "NSUkase987tbjdhscb0928763HHS";

    //This is hashing algorithm using which we will encrypt and decrypt out JWT tokens
    private final Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);

    public String createJWT(Long userID){
        return JWT.create()
                .withSubject(userID.toString())
                .withIssuedAt(new Date())
                //TODO: set ad token expiry parameter
                //.withExpiresAt(new Date())
                .sign(algorithm);
    }

    public Long getSubjectUserId(String jwtString){
        var decodedJwt = JWT.decode(jwtString);
        return Long.valueOf(decodedJwt.getSubject());
    }
}
