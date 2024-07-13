package com.blogging.app.service;

import com.blogging.app.dto.UserDto;
import com.blogging.app.entities.UserEntity;
import com.blogging.app.mapper.MyMapper;
import com.blogging.app.repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserDto createUser(UserDto userDto){
        UserEntity userRequest = MyMapper.dtoToUser(userDto);
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserEntity user = usersRepository.save(userRequest);
        return MyMapper.userToDto(user);
    }

    public UserDto getLoggedInUser(String username, String password){
        UserEntity user = usersRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        if(!passwordEncoder.matches(password,user.getPassword()))
            throw new InvalidCredentials();

        return MyMapper.userToDto(user);
    }

     public static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(String username){
            super("user with name: "+username+" is not found");
        }

        public UserNotFoundException(Long id){
            super("User with id: "+id+" is not found");
        }
    }

    public static class InvalidCredentials extends IllegalArgumentException{
        public InvalidCredentials(){
            super("Invalid username or password combination");
        }
    }
}
