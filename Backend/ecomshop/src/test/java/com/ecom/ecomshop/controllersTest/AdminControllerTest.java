package com.ecom.ecomshop.controllersTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.ecom.ecomshop.controller.AdminController;
import com.ecom.ecomshop.model.Admin;
import com.ecom.ecomshop.repository.AdminRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminController adminController;

    @Test
    public void testGetAdmin() {
        Admin admin1 = new Admin();  // Configure properties as needed
        Admin admin2 = new Admin();  // Configure properties as needed
        when(adminRepository.findAll()).thenReturn(Arrays.asList(admin1, admin2));

        List<Admin> admins = adminController.testConnection();

        assertNotNull(admins);
        assertEquals(2, admins.size());
        verify(adminRepository).findAll();
    }

    @Test
    public void testGetAdminProfileFound() {
        Admin admin = new Admin();
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));

        ResponseEntity<Admin> response = adminController.getAdminProfile(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(admin, response.getBody());
    }

    @Test
    public void testGetAdminProfileNotFound() {
        when(adminRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Admin> response = adminController.getAdminProfile(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() == null);
    }

    @Test
    public void testConnexionSuccessful() {
        Admin admin = new Admin();
        admin.setUsername("test");
        admin.setPassword("testpass");
        when(adminRepository.findByUsernameAndPassword("test", "testpass")).thenReturn(admin);

        ResponseEntity<String> response = adminController.connexion(admin);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertTrue(response.getBody().contains("\"login\": true"));
    }

    @Test
    public void testConnexionUnsuccessful() {
        Admin admin = new Admin();
        admin.setUsername("test");
        admin.setPassword("wrongpass");
        when(adminRepository.findByUsernameAndPassword("test", "wrongpass")).thenReturn(null);

        ResponseEntity<String> response = adminController.connexion(admin);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Identifiants incorrects", response.getBody());
    }

    @Test
    public void testRegisterAdminSuccess() {
        Admin admin = new Admin();
        admin.setUsername("newAdmin");
        when(adminRepository.findByUsername("newAdmin")).thenReturn(null);
        when(adminRepository.save(any())).thenReturn(admin);

        String result = adminController.registerAdmin(admin);

        assertEquals("Admin registered successfully", result);
    }

    @Test
    public void testRegisterAdminExists() {
        Admin admin = new Admin();
        admin.setUsername("existingAdmin");
        when(adminRepository.findByUsername("existingAdmin")).thenReturn(admin);

        String result = adminController.registerAdmin(admin);

        assertEquals("Admin already exists", result);
    }
}
