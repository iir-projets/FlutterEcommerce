package com.ecom.ecomshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecomshop.model.Utilisateur;
import com.ecom.ecomshop.repository.UtilisateurRepository;

@RestController
public class UtilisateurController {
    
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/utilisateur")
    public List<Utilisateur> utilisateur() {
        return utilisateurRepository.findAll();
    }
}
