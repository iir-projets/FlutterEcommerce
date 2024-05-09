package com.ecom.ecomshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecomshop.model.Admin;
import com.ecom.ecomshop.repository.AdminRepository;

@RestController
public class AdminController {
    
    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/connexion")
    public String connexion(@RequestBody Admin admin) {
        Admin adminFromDB = adminRepository.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
        if (adminFromDB != null) {
            return "Connexion réussie";
        } else {
            return "Identifiants incorrects";
        }
    }

}
