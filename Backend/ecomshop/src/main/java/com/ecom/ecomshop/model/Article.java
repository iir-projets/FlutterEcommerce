package com.ecom.ecomshop.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "article")
public class Article {

    // ATTRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 500)
    private String description;

    // hadik precision pour stocker 10 chiffres au total o scale: 2 apres la virgule
    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false) // Vous pouvez ajuster les paramètres en fonction de vos besoins
    private byte[] image; // Utilisation d'un tableau de bytes pour stocker l'image

    // Constructors
    public Article() {
    }

    // Getters and Setters
    /* ********************** ID ARTICLE START  ********************** */
    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
    /* ********************** ID ARTICLE END  ********************** */

    /* ********************** NAME START  ********************** */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /* ********************** NAME END  ********************** */

    /* ********************** DESCRIPTION START  ********************** */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    /* ********************** DESCRIPTION END  ********************** */
    /* ********************** PRICE START  ********************** */

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    /* ********************** PRICE END  ********************** */

    /* ********************** IMAGE START  ********************** */
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    /* ********************** IMAGE END  ********************** */

}