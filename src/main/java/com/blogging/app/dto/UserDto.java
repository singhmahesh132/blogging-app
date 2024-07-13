package com.blogging.app.dto;

import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.lang.NonNull;

@Builder
@Getter
@Setter
public class UserDto {

    @Nullable
    private Long id;

    @NonNull
    private String username;

    @Nullable
    private String password;

    @Nullable
    private String email;

    @Nullable
    private String bio;

    @Nullable
    private String image;

    private String loginToken;
}
