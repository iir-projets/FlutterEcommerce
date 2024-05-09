package com.ecom.ecomshop.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecomshop.model.Utilisateur;
import com.ecom.ecomshop.repository.UtilisateurRepository;

@RestController
public class UtilisateurController {
    
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping("/connexion")
    public String connexion(@RequestBody Utilisateur utilisateur) {
        Utilisateur utilisateurFromDB = utilisateurRepository.findByEmailAndPassword(utilisateur.getEmail(), utilisateur.getPassword());
        if (utilisateurFromDB != null) {
            return "Connexion réussie";
        } else {
            return "Identifiants incorrects";
        }
    }
    
    @PostMapping("/inscription")
    public String inscription(@RequestBody Utilisateur utilisateur) {
        Utilisateur utilisateurFromDB = utilisateurRepository.findByEmail(utilisateur.getEmail());
        if (utilisateurFromDB != null) {
            return "Adresse e-mail déjà utilisée";
        } else {
            utilisateurRepository.save(utilisateur);
            return "Inscription réussie";
        }
    }

     @PutMapping("/utilisateur/{id}")
    public String modifierUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateurModifie) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);
        if (utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();
            if (utilisateurModifie.getNom() != null) {
                utilisateur.setNom(utilisateurModifie.getNom());
            }
            if (utilisateurModifie.getPrenom() != null) {
                utilisateur.setPrenom(utilisateurModifie.getPrenom());
            }
            if (utilisateurModifie.getEmail() != null) {
                utilisateur.setEmail(utilisateurModifie.getEmail());
            }
            if (utilisateurModifie.getTelephone() != null) {
                utilisateur.setTelephone(utilisateurModifie.getTelephone());
            }
            if (utilisateurModifie.getAddress() != null) {
                utilisateur.setAddress(utilisateurModifie.getAddress());
            }
            if (utilisateurModifie.getPassword() != null) {
                utilisateur.setPassword(utilisateurModifie.getPassword());
            }
            utilisateurRepository.save(utilisateur);
            return "Informations utilisateur modifiées avec succès";
        } else {
            return "Utilisateur non trouvé";
        }
    }
}