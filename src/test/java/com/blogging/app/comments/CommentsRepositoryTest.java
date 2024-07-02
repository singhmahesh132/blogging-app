package com.blogging.app.comments;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class CommentsRepositoryTest {

    @Autowired
    private CommentsRepository commentsRepository;

    @Test
    @Order(1)
    void can_create_comments(){
        var comment = CommentEntity.builder()
                .title("my comment")
                .body("hello this is my comment")
                .build();

        commentsRepository.save(comment);
    }

}
