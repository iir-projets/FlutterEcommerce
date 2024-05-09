package com.ecom.ecomshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecomshop.model.Categorie;
import com.ecom.ecomshop.repository.CategorieRepository;

@RestController
public class CategorieController {
    
    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping("/categorie")
    public List<Categorie> categorie() {
        return categorieRepository.findAll();
    }
}
