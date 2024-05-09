package com.ecom.ecomshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecomshop.model.DetailsCommande;
import com.ecom.ecomshop.repository.DetailCommandeRepository;

@RestController
public class DetailsCommandeController {
    
    @Autowired
    private DetailCommandeRepository detailCommandeRepository;

    @GetMapping("/detailCommande")
    public List<DetailsCommande> detailCommande() {
        return detailCommandeRepository.findAll();
    }
}
