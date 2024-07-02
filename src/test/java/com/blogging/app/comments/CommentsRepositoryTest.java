package com.blogging.app.comments;

import com.blogging.app.articles.ArticleEntity;
import com.blogging.app.articles.ArticlesRepository;
import com.blogging.app.users.UserEntity;
import com.blogging.app.users.UserRepositoryTests;
import com.blogging.app.users.UsersRepository;
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
                .build();
        usersRepository.save(author);

        var article = ArticleEntity.builder()
                .title("mahesh")
                .slug("i dont know")
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

}
