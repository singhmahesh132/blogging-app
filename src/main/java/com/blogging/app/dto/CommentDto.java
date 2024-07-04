package com.blogging.app.dto;

import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;

@Getter
@Builder
public class CommentDto {

    @Nullable
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String body;

    @NonNull
   private Date createdAt;

    @NonNull
    private ArticleDto article;

    @NonNull
    private UserDto author;
}
