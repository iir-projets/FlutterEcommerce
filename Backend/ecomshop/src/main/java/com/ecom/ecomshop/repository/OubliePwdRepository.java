package com.ecom.ecomshop.repository;

import com.ecom.ecomshop.model.Article;
import com.ecom.ecomshop.model.OubliePwd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OubliePwdRepository extends JpaRepository<Article, Long> {

    void save(OubliePwd oubliePwd);
    
}