package com.ecom.ecomshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import com.ecom.ecomshop.model.Article;
import com.ecom.ecomshop.model.Categorie;
import com.ecom.ecomshop.repository.ArticleRepository;
import com.ecom.ecomshop.repository.CategorieRepository;
import jakarta.servlet.http.HttpServletRequest;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    private final Path rootLocation = Paths.get(System.getProperty("user.dir"), "ecomshop", "src", "main", "resources", "static", "images");

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

    // @GetMapping("/articles")
    // public List<Article> getAllArticles() {
    //     return articleRepository.findAll();
    // }

//     @GetMapping("/articles")
// public List<Article> getAllArticles() {
//     List<Article> articles = articleRepository.findAll();
//     articles.forEach(article -> {
//         // Ajouter le chemin complet à l'image
//         String imageName = article.getImage();
//         String imageUrl = "img/" + imageName; // Chemin relatif
//         String fullImageUrl = getBaseUrl() + imageUrl; // Chemin complet
//         article.setImage(fullImageUrl);
//     });
//     return articles;
// }

@GetMapping("/articlesAll")
public ResponseEntity<Object> getAllArticles() {
    List<Article> articles = articleRepository.findAll();

    // Parcourir chaque article pour ajouter le chemin complet de l'image
    articles.forEach(article -> {
        String imageName = article.getImage();
        String imageUrl = "images/" + imageName; // Chemin complet de l'image
        article.setImage(imageUrl);
    });

    // Créer un objet pour stocker le statut et les produits
    Map<String, Object> responseData = new HashMap<>();

    // Ajouter le statut à l'objet de réponse
    responseData.put("status", "true");

    // Ajouter la liste des produits à l'objet de réponse
    responseData.put("products", articles);

    // Retourner la réponse JSON avec le statut et les produits
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseData);
}

private String getBaseUrl() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    String baseUrl = request.getRequestURL().toString();
    return baseUrl.substring(0, baseUrl.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
}


    @PostMapping("/addArticle")
    public ResponseEntity<String> ajouterArticle(@RequestParam("name") String name,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("price") BigDecimal price,
                                                 @RequestParam("quantite") int quantite,
                                                 @RequestParam("categorieId") Long categorieId,
                                                 @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            // Create and save the article to generate an ID
            Article article = new Article();
            article.setName(name);
            article.setDescription(description);
            article.setPrice(price);
            article.setQuantite(quantite);
            article.setImage("default.jpg");  // Initially set to default image

            // Retrieve the category from database
            Optional<Categorie> categorieOptional = categorieRepository.findById(categorieId);
            if (categorieOptional.isPresent()) {
                article.setCategorie(categorieOptional.get());
            } else {
                return ResponseEntity.badRequest().body("Category not found with ID: " + categorieId);
            }

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

    @PutMapping("/updateArticle/{id}")
    public ResponseEntity<String> modifierArticle(@PathVariable Long id, @RequestBody Article articleModifie) {
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
            if (articleModifie.getCategorie() != null) {
                article.setCategorie(articleModifie.getCategorie());
            }
            articleRepository.save(article);
            return ResponseEntity.ok("Article modifié avec succès");
        } else {
            return ResponseEntity.badRequest().body("Article non trouvé");
        }
    }

    @DeleteMapping("/dropArticle/{id}")
    public ResponseEntity<String> supprimerArticle(@PathVariable Long id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
            return ResponseEntity.ok("Article supprimé avec succès");
        } else {
            return ResponseEntity.badRequest().body("Article non trouvé");
        }
    }

    private String getExtension(String originalFilename) {
        String[] parts = originalFilename.split("\\.");
        return parts[parts.length - 1];
    }
}
