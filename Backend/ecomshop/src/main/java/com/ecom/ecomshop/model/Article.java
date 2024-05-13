package com.ecom.ecomshop.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "article")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    private String name;
    private String description;
    private BigDecimal price;
    private String image; // Chemin vers l'image
    private int quantite; // Attribut quantite

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
    @JoinColumn(name = "categorie_id") // Corrected to "categorie_id"
    @JsonIgnore // To prevent recursive serialization
    private Categorie categorie; 

    public Article() {
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
    
    public int getQuantite() {
        return quantite;
    }
    public Long getId() {
        return articleId;
    }

    public void setId(Long id) {
        this.articleId = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

}
