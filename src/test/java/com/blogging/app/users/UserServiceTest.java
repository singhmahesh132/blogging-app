package com.blogging.app.users;

import com.blogging.app.dto.UserDto;
import com.blogging.app.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void can_create_user(){
        var user = userService.createUser(UserDto.builder().username("mahesh").email("sss@gmail.com").password("acv").build());
        Assertions.assertNotNull(user);
        Assertions.assertEquals("mahesh",user.getUsername());
    }
}
