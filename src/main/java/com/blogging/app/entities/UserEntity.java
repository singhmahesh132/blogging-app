package com.blogging.app.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.lang.NonNull;

@Entity(name = "users")
@Builder
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @NonNull
    private String username;

    @NonNull
    private String email;

    @Nullable
    private String bio;

    @Nullable
    private  String image;


}
