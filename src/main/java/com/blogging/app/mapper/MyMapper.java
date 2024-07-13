package com.blogging.app.mapper;

import com.blogging.app.dto.ArticleDto;
import com.blogging.app.dto.CommentDto;
import com.blogging.app.dto.UserDto;
import com.blogging.app.entities.ArticleEntity;
import com.blogging.app.entities.CommentEntity;
import com.blogging.app.entities.UserEntity;

public class MyMapper {

    public static UserDto userToDto(UserEntity user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .bio(user.getBio())
                .image(user.getImage())
                .password(null)
                .build();
    }

    public static UserEntity dtoToUser(UserDto userDto){
        return UserEntity.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .bio(userDto.getBio())
                .image(userDto.getImage())
                .build();
    }

    public static ArticleDto articleToDto(ArticleEntity article){
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .slug(article.getSlug())
                .subtitle(article.getSubtitle())
                .body(article.getBody())
                .createdAt(article.getCreatedAt())
                .author(MyMapper.userToDto(article.getAuthor()))
                .build();
    }

    public static ArticleEntity dtoToArticle(ArticleDto articleDto){
        return ArticleEntity.builder()
                .title(articleDto.getTitle())
                .slug(articleDto.getSlug())
                .subtitle(articleDto.getSubtitle())
                .body(articleDto.getBody())
                .author(MyMapper.dtoToUser(articleDto.getAuthor()))
                .build();
    }

    public static CommentDto commentToDto(CommentEntity comment){
        return CommentDto.builder()
                .id(comment.getId())
                .title(comment.getTitle())
                .body(comment.getBody())
                .createdAt(comment.getCreatedAt())
                .author(MyMapper.userToDto(comment.getAuthor()))
                .article(MyMapper.articleToDto(comment.getArticle()))
                .build();
    }

    public static CommentEntity commentToDto(CommentDto commentDto){
        return CommentEntity.builder()
                .title(commentDto.getTitle())
                .body(commentDto.getBody())
                .author(MyMapper.dtoToUser(commentDto.getAuthor()))
                .article(MyMapper.dtoToArticle(commentDto.getArticle()))
                .build();
    }

}
