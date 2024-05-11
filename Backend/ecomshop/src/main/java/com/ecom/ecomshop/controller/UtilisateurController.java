package com.ecom.ecomshop.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // pour le test : http://192.168.56.1:8081/getUser
    @GetMapping("/user/getUser")
    public List<Utilisateur> utilisateur() {
        return utilisateurRepository.findAll();
    }

    // Pour le test : http://192.168.56.1:8081/getUserId/4
    @GetMapping("/user/getUserId/{user_id}")
    public Optional<Utilisateur> obtenirUtilisateurParId(@PathVariable long user_id) {
        return utilisateurRepository.findById(user_id);
    }
    
    @PostMapping("/user/etud/connexion")
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

// test b thunder b form ou json 
// {
//     "adresse": "gueliz",
//     "email": "example@example.com",
//     "nom": "salma",
//     "password": "salma123",
//     "prenom": "batrahi",
//     "telephone": "728288223"
//   }
@PostMapping("/user/inscription")
    public ResponseEntity<String> inscription(@RequestBody Utilisateur utilisateur) {
        Utilisateur utilisateurFromDB = utilisateurRepository.findByEmail(utilisateur.getEmail());
        if (utilisateurFromDB != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Adresse e-mail déjà utilisée");
        } else {
            utilisateurRepository.save(utilisateur);
            return ResponseEntity.status(HttpStatus.CREATED).body("Inscription réussie");
        }
    }

    // bach testiw 
    // {
    //     "address": "gueliz",
    //     "email": "salma@gmail.com",
    //     "prenom": "Migo"
    //   }
      

     @PutMapping("/user/modifierUser/{id}")
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

    // pour le test : http://192.168.56.1:8081/userId/5
    @GetMapping("/user/deleteUser/{user_id}")
    public String supprimerUtilisateurParId(@PathVariable long user_id) {
        utilisateurRepository.deleteById(user_id);
        return "Utilisateur supprimé avec succès";
    }
    
}