package com.blogging.app;

import com.blogging.app.security.JWTAuthenticationFilter;
import com.blogging.app.security.JWTAuthenticationManager;
import com.blogging.app.security.JWTService;
import com.blogging.app.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BloggingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingAppApplication.class, args);
	}

	@Bean
	public PasswordEncoder encryptPassword(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	JWTAuthenticationFilter jwtAuthenticationFilter(JWTService jwtService, UserService userService) throws Exception{
		return new JWTAuthenticationFilter(
				new JWTAuthenticationManager(jwtService,userService));
	}

}
