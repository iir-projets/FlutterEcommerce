package com.ecom.ecomshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecomshop.model.Utilisateur;
import com.ecom.ecomshop.repository.UtilisateurRepository;

@RestController
public class UtilisateurController {
    
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/getUser")
    public List<Utilisateur> utilisateur() {
        return utilisateurRepository.findAll();
    }

    @GetMapping("/getUserId/{user_id}")
    public Optional<Utilisateur> obtenirUtilisateurParId(@PathVariable long user_id) {
        return utilisateurRepository.findById(user_id);
    }
    
    // @PostMapping("/connexion")
    // public String connexion(@RequestBody Utilisateur utilisateur) {
    //     Utilisateur utilisateurFromDB = utilisateurRepository.findByEmailAndPassword(utilisateur.getEmail(), utilisateur.getPassword());
    //     if (utilisateurFromDB != null) {
    //         return "Connexion réussie";
    //     } else {
    //         return "Identifiants incorrects";
    //     }
    // }
   @PostMapping("/etud/connexion")
@ResponseBody
public ResponseEntity<String> connexion(@RequestParam("email") String email, @RequestParam("password") String password) {
    // Retrieve the user by email
    Utilisateur utilisateurFromDB = utilisateurRepository.findByEmail(email);
    
    if (utilisateurFromDB != null) {
        // Verify the password matches
        if (utilisateurFromDB.getPassword().equals(password)) {
            // Create a JSON response
            String data="{\"login\" : true,\"user\" : "+ utilisateurFromDB.toJson()+" }";
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
            
        } else {
            // Return 401 Unauthorized status for incorrect password
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
        }
    } else {
        // Return 404 Not Found status for email not found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Adresse email non trouvée");
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

    @GetMapping("/utilisateurId/{user_id}")
    public String supprimerUtilisateurParId(@PathVariable long user_id) {
        utilisateurRepository.deleteById(user_id);
        return "Utilisateur supprimé avec succès";
    }
    
}