package com.ecom.ecomshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecom.ecomshop.model.Categorie;
import com.ecom.ecomshop.repository.CategorieRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CategorieController {

    @Autowired
    private CategorieRepository categorieRepository;
    private final Path rootLocation = Paths.get(System.getProperty("user.dir"), "ecomshop","src", "main", "resources", "static", "images","categories");

    @GetMapping("/categorie")
    public ResponseEntity<Object> listeCategories() {
        // Retrieve all categories from the repository
        List<Categorie> categories = categorieRepository.findAll();

        // Iterate over each category to add the full image path
        categories.forEach(category -> {
            String imageName = category.getImage();
            String imageUrl = rootLocation + imageName; // Full image path
            category.setImage(imageUrl);
        });

        // Create a response object to store status and categories
        Map<String, Object> responseData = new HashMap<>();

        // Add status to the response object
        responseData.put("status", "true");

        // Add the list of categories to the response object
        responseData.put("categories", categories);

        // Return the JSON response with status and categories
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseData);
    }

    @PostMapping("/ajouterCategorie")
    public ResponseEntity<String> ajouterCategorie(@RequestParam("catNom") String catNom, 
                                                   @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            // Create a new category instance
            Categorie categorie = new Categorie();
            categorie.setCatNom(catNom);
            categorie.setImage("default.jpg");
            categorie = categorieRepository.save(categorie);

            // Process and save the image if it's provided
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageName = categorie.getCatId()+"."+ getExtension(imageFile.getOriginalFilename());
                Path targetLocation = rootLocation.resolve(imageName);
                Files.copy(imageFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                categorie.setImage(imageName);
                categorieRepository.save(categorie);
            }

            // Save the category
            categorieRepository.save(categorie);

            return ResponseEntity.ok("Catégorie ajoutée avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add category: " + e.getMessage());
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

    @PutMapping("/categorie/{id}")
    public String modifierCategorie(@PathVariable Long id, @RequestBody Categorie categorieModifie) {
        Optional<Categorie> categorieOptional = categorieRepository.findById(id);
        if (categorieOptional.isPresent()) {
            Categorie categorie = categorieOptional.get();
            if (categorieModifie.getCatNom() != null) {
                categorie.setCatNom(categorieModifie.getCatNom());
            }
            // Vérifier si des modifications ont été apportées avant de sauvegarder
            if (!categorie.equals(categorieModifie)) {
                categorieRepository.save(categorie);
                return "Catégorie modifiée avec succès";
            } else {
                return "Aucune modification détectée";
            }
        } else {
            return "Catégorie non trouvée";
        }
    }

    @DeleteMapping("/categorie/{id}")
    public String supprimerCategorie(@PathVariable Long id) {
        categorieRepository.deleteById(id);
        return "Catégorie supprimée avec succès";
    }
}
