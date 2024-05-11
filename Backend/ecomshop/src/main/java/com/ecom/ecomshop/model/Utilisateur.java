package com.ecom.ecomshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class Utilisateur {

    // ATTRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 255)
    private String nom;

    @Column(nullable = false, length = 255)
    private String prenom;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(length = 255)
    private String telephone;

    @Column(length = 255)
    private String address;

    @Column(nullable = false, length = 255)
    private String password;

    // Constructors
    public Utilisateur() {
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    /* ********************** UTILISATEUR NOM END  ********************** */
    /* ********************** UTILISATEUR PRENOM START  ********************** */

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    /* ********************** UTILISATEUR PRENOM END  ********************** */
    /* ********************** UTILISATEUR EMAIL START  ********************** */

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    /* ********************** UTILISATEUR EMAIL END  ********************** */
    /* ********************** UTILISATEUR TELEPHONE START  ********************** */

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    /* ********************** UTILISATEUR TELEPHONE END  ********************** */
    /* ********************** UTILISATEUR ADRESSE START  ********************** */

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    /* ********************** UTILISATEUR ADRESSE END  ********************** */
    /* ********************** UTILISATEUR PASSWORD START  ********************** */

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /* ********************** UTILISATEUR PASSWORD END  ********************** */
    /* ********************** TO JSON START  ********************** */
    public String toJson() {
        return "{\"user_id\": " + this.userId + ", \"nom\": \"" + this.nom + "\", \"prenom\": \"" + this.prenom + "\", \"email\": \"" + this.email + "\", \"adresse\": \"" + this.address + "\", \"telephone\": \"" + this.telephone + "\"}";
    }    
    
    /* ********************** TO JSON END  ********************** */



}
