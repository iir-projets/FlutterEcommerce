package com.ecom.ecomshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ecom.ecomshop.model.Article;
import com.ecom.ecomshop.repository.ArticleRepository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    private final Path rootLocation = Paths.get(System.getProperty("user.dir"), "ecomshop","src", "main", "resources", "static", "images");


    @jakarta.annotation.PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (Exception e) {
            throw new RuntimeException("Could not create the image storage directory", e);
        }
    }

    @GetMapping("/testConnection")
    public List<Article> testConnection() {
        return articleRepository.findAll();
    }

    @GetMapping("/articles")
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @PostMapping("/addArticle")
    public ResponseEntity<String> ajouterArticle(@RequestParam("name") String name,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("price") BigDecimal price,
                                                 @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            // Create and save the article to generate an ID
            Article article = new Article();
            article.setName(name);
            article.setDescription(description);
            article.setPrice(price);
            article.setImage("default.jpg");  // Initially set to default image
            article = articleRepository.save(article);  // Save to generate ID
    
            // Process and save the image if it's provided
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageName = article.getId() + "." + getExtension(imageFile.getOriginalFilename());
                Path targetLocation = rootLocation.resolve(imageName);
                Files.copy(imageFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                article.setImage(imageName);  // Update article with the actual image name
                articleRepository.save(article);  // Save the updated article
            }
    
            return ResponseEntity.ok("Article ajouté avec succès: " + article.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add article: " + e.getMessage());
        }
    }
    
    private static String getExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return "";
        }
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex >= 0) {
            return filename.substring(dotIndex + 1);
        } else {
            return "";
        }
    }
    

    @PutMapping("/updateArticle/{id}")
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
            if (articleModifie.getImage() != null) {
                article.setImage(articleModifie.getImage());
            }
            articleRepository.save(article);
            return "Article modifié avec succès";
        } else {
            return "Article non trouvé";
        }
    }

    @DeleteMapping("/dropArticle/{id}")
    public String supprimerArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return "Article supprimé avec succès";
    }


    @GetMapping("/articles")
    public Page<Article> getAllArticles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return articleRepository.findAll(pageable);

            }

            
    }


    
    


