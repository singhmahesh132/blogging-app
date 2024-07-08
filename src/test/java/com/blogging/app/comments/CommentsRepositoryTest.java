package com.blogging.app.comments;

import com.blogging.app.entities.ArticleEntity;
import com.blogging.app.repos.ArticlesRepository;
import com.blogging.app.entities.CommentEntity;
import com.blogging.app.entities.UserEntity;
import com.blogging.app.repos.CommentsRepository;
import com.blogging.app.repos.UsersRepository;
import org.junit.jupiter.api.Assertions;
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

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ArticlesRepository articlesRepository;

    @Test
    @Order(1)
    void can_create_comments(){
        var author = UserEntity.builder()
                .username("mahesh")
                .email("singhm@gmail.com")
                .password("abc")
                .build();
        usersRepository.save(author);

        var article = ArticleEntity.builder()
                .title("mahesh")
                .slug("i don't know")
                .body("my book")
                .author(author)
                .build();
        articlesRepository.save(article);

        var comment = CommentEntity.builder()
                .title("my comment")
                .body("hello this is my comment")
                .article(article)
                .author(author)
                .build();
        commentsRepository.save(comment);
    }

    @Test
    @Order(2)
    void can_find_user(){
        can_create_comments();
        var comment = commentsRepository.findAll();

        Assertions.assertEquals(1,comment.size());
    }

}
