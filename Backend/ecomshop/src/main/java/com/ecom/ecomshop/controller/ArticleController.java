package com.ecom.ecomshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ecom.ecomshop.model.Article;
import com.ecom.ecomshop.repository.ArticleRepository;

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

    private final Path rootLocation = Paths.get(System.getProperty("user.dir"), "ecomshop", "img");

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
                                                 @RequestParam("image") MultipartFile imageFile) {
        try {
            Article article = new Article();
            article.setName(name);
            article.setDescription(description);
            article.setPrice(price);

            if (imageFile != null && !imageFile.isEmpty()) {
                String imageName = saveImage(imageFile);
                article.setImage(imageName);
            }

            articleRepository.save(article);
            return ResponseEntity.ok("Article ajouté avec succès: " + article.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add article: " + e.getMessage());
        }
    }

    private String saveImage(MultipartFile file) throws Exception {
        String filename = UUID.randomUUID().toString() + "." + getExtension(file.getOriginalFilename());
        Path targetLocation = rootLocation.resolve(filename);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return filename;
    }

    private static String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
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
}
