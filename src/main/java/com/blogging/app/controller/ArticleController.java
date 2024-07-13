package com.blogging.app.controller;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @GetMapping("/get-all")
    public String getAllArticles(){
        return " all the articles";
    }

    @PostMapping("/create")
    public String createArticle(){
        return "article is created";
    }
}
