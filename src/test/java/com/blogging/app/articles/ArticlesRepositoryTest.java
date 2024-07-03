package com.blogging.app.articles;

import com.blogging.app.entities.ArticleEntity;
import com.blogging.app.entities.UserEntity;
import com.blogging.app.repos.ArticlesRepository;
import com.blogging.app.repos.UsersRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class ArticlesRepositoryTest {

    @Autowired
    private ArticlesRepository articlesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @Order(1)
    void can_create_articles(){
        var author = UserEntity.builder()
                .username("mahesh")
                .email("singh.130@gmail.com")
                .build();

        usersRepository.save(author);

        var article = ArticleEntity.builder()
                .author(author)
                .title("my article")
                .slug("my slug")
                .body("my article body")
                .build();

        articlesRepository.save(article);
    }
}
