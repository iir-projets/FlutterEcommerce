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

    @PostMapping("/admin/connexion") // Changed path to ensure no conflict
    public String connexion(@RequestBody Admin admin) {
        Admin adminFromDB = adminRepository.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
        if (adminFromDB != null) {
            return "Connexion réussie";
        } else {
            return "Identifiants incorrects";
        }
    }
    
    @PostMapping("/admin/register") 
    public String registerAdmin(@RequestBody Admin admin) {
        Admin existingAdmin = adminRepository.findByUsername(admin.getUsername());
        if (existingAdmin != null) {
            return "Admin already exists";
        }
        adminRepository.save(admin);
        return "Admin registered successfully";
    }
}
