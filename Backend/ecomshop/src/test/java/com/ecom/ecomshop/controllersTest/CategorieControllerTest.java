package com.ecom.ecomshop.controllersTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ecom.ecomshop.controller.CategorieController;
import com.ecom.ecomshop.model.Categorie;
import com.ecom.ecomshop.repository.CategorieRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CategorieController.class)
public class CategorieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategorieRepository categorieRepository;

    @Test
    public void testListeCategories() throws Exception {
        Categorie categorie = new Categorie();
        categorie.setCatId(1L);
        categorie.setCatNom("Electronics");
        categorie.setImage("default.jpg");

        when(categorieRepository.findAll()).thenReturn(Arrays.asList(categorie));

        mockMvc.perform(MockMvcRequestBuilders.get("/categorie"))
            .andExpect(status().isAccepted())
            .andExpect(MockMvcResultMatchers.jsonPath("$.categories[0].catNom").value("Electronics"));
    }

   /* @Test
    public void testAjouterCategorieWithImage() throws Exception {
        MockMultipartFile imageFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "Test Image Content".getBytes());
        Categorie categorie = new Categorie();
        categorie.setCatNom("New Category");

        mockMvc.perform(MockMvcRequestBuilders.multipart("/ajouterCategorie")
                .file(imageFile)
                .param("catNom", "New Category")
                .contentType(MediaType.MULTIPART_FORM_DATA))
            .andExpect(status().isOk());
    }*/

    @Test
    public void testModifierCategorie() throws Exception {
        Categorie existingCategorie = new Categorie();
        existingCategorie.setCatId(1L);
        existingCategorie.setCatNom("Original Category");

        Categorie modifiedCategorie = new Categorie();
        modifiedCategorie.setCatNom("Updated Category");

        when(categorieRepository.findById(1L)).thenReturn(Optional.of(existingCategorie));

        mockMvc.perform(MockMvcRequestBuilders.put("/categorie/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"catNom\":\"Updated Category\"}"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Catégorie modifiée avec succès"));
    }

    @Test
    public void testSupprimerCategorie() throws Exception {
        when(categorieRepository.existsById(1L)).thenReturn(true);
        doNothing().when(categorieRepository).deleteById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/categorie/{id}", 1L))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Catégorie supprimée avec succès"));
    }
}
