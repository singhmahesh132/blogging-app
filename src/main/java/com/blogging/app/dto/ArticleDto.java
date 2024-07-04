package com.blogging.app.dto;

import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;

@Getter
@Builder
public class ArticleDto {

    @Nullable
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String slug;

    @Nullable
    private String subtitle;

    @NonNull
    private String body;

    @Nullable
    private Date createdAt;

    @NonNull
    private UserDto author;
}
