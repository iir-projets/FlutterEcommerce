package com.ecom.ecomshop.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.ecom.ecomshop.model.Admin;
import com.ecom.ecomshop.repository.AdminRepository;

@RestController
public class AdminController {
    
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/getAdmin")
    public List<Admin> testConnection() {
        return adminRepository.findAll();
    }
    @GetMapping("/admin/profile")
    public ResponseEntity<Admin> getAdminProfile(@RequestParam("userId") Long userId) {
        Optional<Admin> adminOptional = adminRepository.findById(userId);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/admin/connexion")
    @ResponseBody
    public ResponseEntity<String> connexion(@RequestBody Admin admin) {
        // Retrieve the admin by username and password
        Admin adminFromDB = adminRepository.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
        
        if (adminFromDB != null) {
            // Create a JSON response indicating successful login
            String data = "{\"login\": true, \"admin\": " + adminFromDB.toJson() + "}";
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
        } else {
            // Return 401 Unauthorized status for incorrect username or password
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants incorrects");
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
