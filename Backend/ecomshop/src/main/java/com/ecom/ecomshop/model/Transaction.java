package com.ecom.ecomshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

    // ATTRIBUTS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    private Date transactionDate;

    @Column(nullable = false, length = 255)
    private String transactionStatus;

    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    private Utilisateur user;

    // Constructors
    public Transaction() {
    }

    // Getters and Setters
    /* ********************** TRANSACTION ID START  ********************** */

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
    /* ********************** TRANSACTION ID END  ********************** */
    /* ********************** TRANSACTION DATE START  ********************** */

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    /* ********************** TRANSACTION DATE END  ********************** */
    /* ********************** TRANSACTION STATUS START  ********************** */

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
    /* ********************** TRANSACTION STATUS END  ********************** */

    /* ********************** UTILISATEUR START  ********************** */

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
    /* ********************** UTILISATEUR END  ********************** */

}
