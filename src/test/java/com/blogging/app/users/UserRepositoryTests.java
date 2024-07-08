package com.blogging.app.users;

import com.blogging.app.entities.UserEntity;
import com.blogging.app.repos.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTests {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @Order(1)
    void can_create_users(){
        var user = UserEntity.builder()
                .username("mahesh")
                .email("singhm@gmail.com")
                .password("abc")
                .build();

        usersRepository.save(user);
    }

    //Every test runs on fresh data
    //i.e. the above add user data will not be available in this method to test size
    //thus we need to again add the user.
    @Test
    @Order(2)
    void can_find_users(){
        var user = UserEntity.builder()
                .username("mahesh")
                .email("singhm@gmail.com")
                .password("abc")
                .build();
        usersRepository.save(user);
        var users = usersRepository.findAll();
        Assertions.assertEquals(1, users.size());
    }
}
