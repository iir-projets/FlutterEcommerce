package com.ecom.ecomshop.controller;

import com.ecom.ecomshop.model.Article;
import com.ecom.ecomshop.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    private static final String UPLOAD_PATH = "C:/Users/21262/Desktop/e-commerce flutter/FlutterEcommerce/Frontend/dashboard/src/assets/products/";

    @GetMapping("/testConnection")
    public List<Article> testConnection() {
        return articleRepository.findAll();
    }

    @PostMapping("/article")
    @CrossOrigin
    public ResponseEntity<String> ajouterArticle(@RequestParam("image") MultipartFile file,
                                                 @RequestParam String name, @RequestParam String description, @RequestParam BigDecimal price) {

        String fileName = file.getOriginalFilename();
        System.out.println(name);
        System.out.println(description);
        System.out.println(fileName);
        try {
            file.transferTo(new File(UPLOAD_PATH + fileName));

            // Create a new Article object and set its properties
            Article article = new Article();
            article.setName(name);
            article.setDescription(description);
            article.setPrice(price);
            article.setImagePath(fileName); // Set the image path

            // Save the Article object to the database
            articleRepository.save(article);

            return ResponseEntity.ok("File uploaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/article/{id}")
    public String modifierArticle(@PathVariable Long id, @RequestBody Article articleModifie) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();
            if (articleModifie.getName() != null) {
                article.setName(articleModifie.getName());
            }
            if (articleModifie.getDescription() != null) {
                article.setDescription(articleModifie.getDescription());
            }
            if (articleModifie.getPrice() != null) {
                article.setPrice(articleModifie.getPrice());
            }
            // The image path is not modified here because it's assumed that you won't modify the image path directly in a PUT request
            articleRepository.save(article);
            return "Article modifié avec succès";
        } else {
            return "Article non trouvé";
        }
    }

    @DeleteMapping("/article/{id}")
    public String supprimerArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return "Article supprimé avec succès";
    }
}
