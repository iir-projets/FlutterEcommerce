package com.ecom.ecomshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecomshop.model.OubliePwd;
import com.ecom.ecomshop.model.Utilisateur;
import com.ecom.ecomshop.repository.OubliePwdRepository;
import com.ecom.ecomshop.repository.UtilisateurRepository;
import com.ecom.ecomshop.service.SendEmailService;

@RestController
public class EmailController {

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    @Autowired
    private OubliePwdRepository oubliePwdRepository;

    @PostMapping("sendEmail")
    public String sendEmail(@RequestParam String receiver,
                            @RequestParam String subject,
                            @RequestParam String body) {
        // Vérifier si l'utilisateur avec cet email existe déjà dans la base de données
        Utilisateur utilisateur = utilisateurRepository.findByEmail(receiver);
        if (utilisateur == null) {
            // L'email n'existe pas dans la base de données
            return "L'email n'existe pas dans la base de données.";
        }

        // Créer une instance de OubliePwd avec les données de l'email
        OubliePwd oubliePwd = new OubliePwd();
        oubliePwd.setCode(body);
        oubliePwd.setUtilisateur(utilisateur);

        // Enregistrer l'instance de OubliePwd dans la base de données
        oubliePwdRepository.save(oubliePwd);

        // Envoyer l'email
        sendEmailService.sendEmail(receiver, subject, body);
        return "Email envoyé avec succès.";
    }
}
