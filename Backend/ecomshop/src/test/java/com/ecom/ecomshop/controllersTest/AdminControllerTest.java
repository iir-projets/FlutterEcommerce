package com.ecom.ecomshop.controllersTest;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Optional;

import com.ecom.ecomshop.controller.AdminController;
import com.ecom.ecomshop.model.Admin;
import com.ecom.ecomshop.repository.AdminRepository;

public class AdminControllerTest {

    private AdminRepository adminRepository = mock(AdminRepository.class);
    private AdminController adminController = new AdminController(adminRepository);

    @Test
    public void testGetAdmins() {
        Admin admin1 = new Admin(); // Set details as needed
        Admin admin2 = new Admin(); // Set details as needed
        when(adminRepository.findAll()).thenReturn(Arrays.asList(admin1, admin2));
        
        assertEquals(Arrays.asList(admin1, admin2), adminController.testConnection());
    }

    @Test
    public void testGetAdminProfileFound() {
        Admin admin = new Admin();
        admin.setUserId(1L);
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        
        ResponseEntity<Admin> response = adminController.getAdminProfile(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(admin, response.getBody());
    }

    @Test
    public void testGetAdminProfileNotFound() {
        when(adminRepository.findById(anyLong())).thenReturn(Optional.empty());
        
        ResponseEntity<Admin> response = adminController.getAdminProfile(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testConnexionSuccess() {
        Admin admin = new Admin();
        admin.setUsername("user");
        admin.setPassword("pass");
        admin.setEmail("email@example.com");
        when(adminRepository.findByUsernameAndPassword("user", "pass")).thenReturn(admin);
        
        ResponseEntity<String> response = adminController.connexion(admin);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertTrue(response.getBody().contains("\"login\": true"));
    }

    @Test
    public void testConnexionFailure() {
        Admin admin = new Admin();
        admin.setUsername("user");
        admin.setPassword("pass");
        when(adminRepository.findByUsernameAndPassword("user", "wrongpass")).thenReturn(null);
        
        ResponseEntity<String> response = adminController.connexion(admin);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Identifiants incorrects", response.getBody());
    }

    @Test
    public void testRegisterAdminNew() {
        Admin admin = new Admin();
        admin.setUsername("newuser");
        when(adminRepository.findByUsername("newuser")).thenReturn(null);
        when(adminRepository.save(any(Admin.class))).thenReturn(admin);
        
        String result = adminController.registerAdmin(admin);
        assertEquals("Admin registered successfully", result);
    }

    @Test
    public void testRegisterAdminExists() {
        Admin admin = new Admin();
        admin.setUsername("user");
        when(adminRepository.findByUsername("user")).thenReturn(admin);
        
        String result = adminController.registerAdmin(admin);
        assertEquals("Admin already exists", result);
    }
}

