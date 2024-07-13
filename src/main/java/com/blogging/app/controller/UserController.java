package com.blogging.app.controller;

import com.blogging.app.dto.UserDto;
import com.blogging.app.security.JWTService;
import com.blogging.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JWTService jwtService;


    @PostMapping("/create")
    ResponseEntity<UserDto> signUpUser(@RequestBody UserDto userDto){
        UserDto savedUser = userService.createUser(userDto);
        savedUser.setLoginToken(jwtService.createJWT(savedUser.getId()));
        URI savedUserUri = URI.create("/users/"+savedUser.getId());

        return ResponseEntity.created(savedUserUri).body(savedUser);
    }

    @PostMapping("/login")
    ResponseEntity<UserDto> loginUser(@RequestBody UserDto userDto){
        UserDto loggedInUser = userService.getLoggedInUser(userDto.getUsername(),userDto.getPassword());
        loggedInUser.setLoginToken(jwtService.createJWT(loggedInUser.getId()));
        return ResponseEntity.ok(loggedInUser);
    }
}
