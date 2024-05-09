package com.ecom.ecomshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {

    // ATTRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 255)
    private String username;
    @Column(nullable = false, length = 255)
    private String password;

    // Constructors
    public Admin() {
    }

    // Getters and Setters
    /* ********************** UTILISATEUR ID START  ********************** */

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    /* ********************** UTILISATEUR ID END  ********************** */
    /* ********************** UTILISATEUR NOM START  ********************** */

    public String getUsername() {
        return username;
    }

    public void setNom(String username) {
        this.username = username;
    }
    /* ********************** UTILISATEUR NOM END  ********************** */
    /* ********************** UTILISATEUR PASSWORD START  ********************** */

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /* ********************** UTILISATEUR PASSWORD END  ********************** */

}
