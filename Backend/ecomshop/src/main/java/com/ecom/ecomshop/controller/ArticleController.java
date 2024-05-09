package com.ecom.ecomshop.controller;

import com.ecom.ecomshop.model.Article;
import com.ecom.ecomshop.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/testConnection")
    public List<Article> testConnection() {
        return articleRepository.findAll();
    }

    @PostMapping("/article")
    public String ajouterArticle(@RequestBody Article article) {
        articleRepository.save(article);
        return "Article ajouté avec succès";
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
            if (articleModifie.getImage() != null) {
                article.setImage(articleModifie.getImage());
            }
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
