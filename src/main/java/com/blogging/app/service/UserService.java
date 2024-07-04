package com.blogging.app.service;

import com.blogging.app.dto.UserDto;
import com.blogging.app.mapper.MyMapper;
import com.blogging.app.repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    public UserDto createUser(UserDto userDto){
        var user = usersRepository.save(MyMapper.dtoToUser(userDto));
        return MyMapper.userToDto(user);
    }

    public UserDto getUser(UserDto userDto){
        var user = usersRepository.findByUsername(userDto.getUsername()).orElseThrow(() -> new UserNotFoundException(userDto.getUsername()));
        return MyMapper.userToDto(user);
    }

    public UserDto getLoggedInUser(String username, String password){
        var user = usersRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return MyMapper.userToDto(user);
    }

     static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(String username){
            super("user with name: "+username+" is not found");
        }

        public UserNotFoundException(Long id){
            super("User with id : "+id+" is not found");
        }
    }
}
