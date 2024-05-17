package com.ecom.ecomshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.AbstractFileResolvingResource;
import org.springframework.core.io.UrlResource;
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

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
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

    /*@GetMapping("/articles")
    public List<Article> getArticles() {
        List<Article> articles = articleRepository.findAll();
        articles.forEach(article -> {
            if (article.getCategorie() != null) {
                Long categoryId = article.getCategorie().getCatId();
                // Fetch the complete Categorie object from the repository using its ID
                Optional<Categorie> categorieOptional = categorieRepository.findById(categoryId);
                categorieOptional.ifPresent(article::setCategorie); // Set the fetched Categorie object
            }
        });
        return articles;
    }*/

	private final String imageBaseUrl = "http://192.168.56.1:8081/im/";

		
	@GetMapping("/articlesAll")
    public ResponseEntity<Object> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        List<Categorie> categorie = categorieRepository.findAll();

  
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("status", true);

        articles.forEach(article -> {
            String imageName = article.getImage();
            String imageUrl = imageBaseUrl + imageName; // Chemin complet de l'image
            article.setImage(imageUrl);
        });
        responseData.put("products", articles);
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
		                                             @RequestParam("categorieId") int categorieId, // Change to categorieName
		                                             @RequestParam(value = "image", required = false) MultipartFile imageFile) {
		    try {
		        // Create and save the article to generate an ID
		        Article article = new Article();
		        article.setName(name);
		        article.setDescription(description);
		        article.setPrice(price);
		        article.setQuantite(quantite);
		        article.setImage("default.jpg");  // Initially set to default image
		
		        // Retrieve the category from database based on name
		        Optional<Categorie> categorieOptional = categorieRepository.findByCatId(categorieId);
		        if (categorieOptional.isPresent()) {
		            article.setCategorie(categorieOptional.get());
		        } else {
		            return ResponseEntity.badRequest().body("Category not found with id : " + categorieId);
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


	@PutMapping("/updaateArticle/{id}")
	public ResponseEntity<String> modifieerArticle(@PathVariable Long id, @RequestBody Article articleModifie) {
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
	        if (articleModifie.getQuantite() != 0) {
	            article.setQuantite(articleModifie.getQuantite());
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
        articleRepository.deleteById(id);
        return ResponseEntity.ok("Article supprimée avec succès");
    }

    // @DeleteMapping("/dropArticle/{id}")
    // public ResponseEntity<String> supprimerArticle(@PathVariable Long id) {
    //     if (articleRepository.findById(id)) {
    //         articleRepository.deleteById(id);
    //         return ResponseEntity.ok("Article supprimé avec succès");
    //     } else {
    //         return ResponseEntity.badRequest().body("Article non trouvé");
    //     }
    // }

    private String getExtension(String originalFilename) {
        String[] parts = originalFilename.split("\\.");
        return parts[parts.length - 1];
    }
	private final Path rootLocationsafaa = Paths.get(System.getProperty("user.dir"), "ecomshop", "src", "main", "resources", "static", "images");

    @GetMapping("/im/{imageName}")
    public ResponseEntity<UrlResource> getImage(@PathVariable String imageName) {
        try {
            // Construire le chemin d'accès complet à l'image
            Path imagePath = rootLocationsafaa.resolve(imageName);
            UrlResource resource = new UrlResource(imagePath.toUri());

            // Vérifier si l'image existe et est lisible
            if (((AbstractFileResolvingResource) resource).exists() && ((AbstractFileResolvingResource) resource).isReadable()) {
                // Si oui, renvoyer la réponse avec l'image
                return ResponseEntity.ok().body(resource);
            } else {
                // Si l'image n'existe pas ou n'est pas lisible, renvoyer une réponse d'erreur
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            // En cas d'erreur lors de la récupération de l'image, renvoyer une réponse d'erreur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}