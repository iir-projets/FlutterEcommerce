package com.ecom.ecomshop.controller;

import com.ecom.ecomshop.model.Article;
import com.ecom.ecomshop.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleRepository productRepository;

    @GetMapping("/testConnection")
    public List<Article> testConnection() {
        return productRepository.findAll();
    }
}
