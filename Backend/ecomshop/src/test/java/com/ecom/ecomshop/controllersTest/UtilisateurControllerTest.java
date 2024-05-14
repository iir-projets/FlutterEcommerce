package com.ecom.ecomshop.controllersTest;

import com.ecom.ecomshop.controller.UtilisateurController;
import com.ecom.ecomshop.model.Utilisateur;
import com.ecom.ecomshop.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UtilisateurController.class)
class UtilisateurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    @Test
    void testGetUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUser"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUserId/{user_id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testConnexion() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("test@example.com");
        utilisateur.setPassword("test123");

        Mockito.when(utilisateurRepository.findByEmail("test@example.com")).thenReturn(utilisateur);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/connexion")
                .param("email", "test@example.com")
                .param("password", "test123"))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    void testInscription() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("test@example.com");
        utilisateur.setPassword("test123");

        Mockito.when(utilisateurRepository.findByEmail("test@example.com")).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/inscription")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\",\"password\":\"test123\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testModifierUser() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUserId(1L);

        Mockito.when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(utilisateur));

        mockMvc.perform(MockMvcRequestBuilders.put("/user/modifierUser/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\",\"password\":\"test123\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/deleteUser/{user_id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}