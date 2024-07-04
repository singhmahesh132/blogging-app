package com.blogging.app.service;

import com.blogging.app.dto.ArticleDto;
import com.blogging.app.entities.ArticleEntity;
import com.blogging.app.mapper.MyMapper;
import com.blogging.app.repos.ArticlesRepository;
import com.blogging.app.repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticlesRepository articlesRepository;

    @Autowired
    UsersRepository usersRepository;

    public ArticleDto createArticle(@NonNull ArticleDto articleDto, Long authorId){
        var author = usersRepository.findById(authorId).orElseThrow(() -> new UserService.UserNotFoundException(authorId));
        var article = articlesRepository.save(ArticleEntity.builder()
                .title(articleDto.getTitle())
                .body(articleDto.getBody())
                //:TODO: create a proper slugification function
                .slug(articleDto.getSlug().toLowerCase().replaceAll("\\s+","-"))
                .subtitle(articleDto.getSubtitle())
                .author(author)
                .build());

        return MyMapper.articleToDto(article);
    }

    public List<ArticleDto> getAllArticles(){
        var articleList = articlesRepository.findAll();
        List<ArticleDto> articleDtoList = new ArrayList<>();
        articleList.forEach(article -> articleDtoList.add(MyMapper.articleToDto(article)));
        return articleDtoList;
    }

    public ArticleDto findArticleBySlug(String slug){
        var article = articlesRepository.findBySlug(slug).orElseThrow(() -> new ArticleNotFoundException(slug));
        return MyMapper.articleToDto(article);
    }

    static class ArticleNotFoundException extends IllegalArgumentException{
        public ArticleNotFoundException(String slug){
            super("Article with slug :"+slug+" is not found");
        }
    }
}
