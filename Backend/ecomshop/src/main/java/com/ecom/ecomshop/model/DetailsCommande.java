package com.ecom.ecomshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
// had many to one wahd relation "plusieurs-vers-un" entre deux entités drnaha hint endna foreign key dyal article
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detail_commande")
public class DetailsCommande {

    // ATTRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;

    @ManyToOne
    @JoinColumn(name = "articleId", referencedColumnName = "articleId", nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "transactionId", referencedColumnName = "transactionId", nullable = false)
    private Transaction transaction;

    @Column(nullable = false)
    private Integer qty;

    // Constructors
    public DetailsCommande() {
    }

    // Getters and Setters
    /* ********************** DETAIL ID START  ********************** */
    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }
    /* ********************** DETAIL ID END  ********************** */
    /* ********************** QUANTITE START  ********************** */

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    /* ********************** QUANTITE END  ********************** */
    
    /* **********************ARTICLE START  ********************** */
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    /* ********************** ARTICLE END  ********************** */
    /* ********************** TRANSACTION START  ********************** */

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
    /* ********************** TRANSACTION END  ********************** */


}
