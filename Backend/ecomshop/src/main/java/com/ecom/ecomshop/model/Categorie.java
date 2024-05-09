package com.ecom.ecomshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorie")
public class Categorie {

    // ATTRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catId;

    @Column(nullable = false, length = 255)
    private String catNom;

    // Constructors
    public Categorie() {
    }

    // Getters and Setters
    /* ********************** ID CATEGORIE START  ********************** */
    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }
    /* ********************** ID CATEGORIE END  ********************** */

    /* ********************** NOM CATEGORIE START  ********************** */
    public String getCatNom() {
        return catNom;
    }

    public void setCatNom(String catNom) {
        this.catNom = catNom;
    }
    /* ********************** NOM CATEGORIE END  ********************** */

}
