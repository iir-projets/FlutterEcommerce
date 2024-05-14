package com.ecom.ecomshop.controllersTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ecom.ecomshop.controller.EmailController;
import com.ecom.ecomshop.model.OubliePwd;
import com.ecom.ecomshop.model.Utilisateur;
import com.ecom.ecomshop.repository.OubliePwdRepository;
import com.ecom.ecomshop.repository.UtilisateurRepository;
import com.ecom.ecomshop.service.SendEmailService;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EmailController.class)
public class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SendEmailService sendEmailService;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    @MockBean
    private OubliePwdRepository oubliePwdRepository;

    @Test
    public void testSendEmailUserExists() throws Exception {
        // Given
        Utilisateur user = new Utilisateur();
        user.setEmail("test@example.com");

        when(utilisateurRepository.findByEmail("test@example.com")).thenReturn(user);
        doNothing().when(sendEmailService).sendEmail(anyString(), anyString(), anyString());

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/sendEmail")
                .param("receiver", "test@example.com")
                .param("subject", "Test Subject")
                .param("body", "Test Body"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Email envoyé avec succès."));
        
        // Verify
        verify(oubliePwdRepository).save(any(OubliePwd.class));
        verify(sendEmailService).sendEmail("test@example.com", "Test Subject", "Test Body");
    }

    @Test
    public void testSendEmailUserDoesNotExist() throws Exception {
        // Given
        when(utilisateurRepository.findByEmail("test@example.com")).thenReturn(null);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/sendEmail")
                .param("receiver", "test@example.com")
                .param("subject", "Test Subject")
                .param("body", "Test Body"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("L'email n'existe pas dans la base de données."));
    }
}

