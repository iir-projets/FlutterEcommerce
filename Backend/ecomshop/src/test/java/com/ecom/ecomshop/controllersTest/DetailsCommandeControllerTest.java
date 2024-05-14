package com.ecom.ecomshop.controllersTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ecom.ecomshop.controller.DetailsCommandeController;
import com.ecom.ecomshop.model.DetailsCommande;
import com.ecom.ecomshop.repository.DetailCommandeRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DetailsCommandeController.class)
public class DetailsCommandeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DetailCommandeRepository detailCommandeRepository;

    @Test
    public void testListeDetailsCommande() throws Exception {
        DetailsCommande detailsCommande = new DetailsCommande();
        detailsCommande.setDetailId(1L); // Changed from setId to setDetailId
        detailsCommande.setQty(10);

        when(detailCommandeRepository.findAll()).thenReturn(Arrays.asList(detailsCommande));

        mockMvc.perform(MockMvcRequestBuilders.get("/detailCommande"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].detailId").value(1)) // Changed from id to detailId
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].qty").value(10));
    }

    @Test
    public void testAjouterDetailCommande() throws Exception {
        DetailsCommande detailsCommande = new DetailsCommande();
        detailsCommande.setQty(5);

        mockMvc.perform(MockMvcRequestBuilders.post("/ajouterDetailCommande")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"qty\": 5}"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Détail de commande ajouté avec succès"));
    }

    @Test
    public void testModifierDetailCommandeFound() throws Exception {
        DetailsCommande existingDetail = new DetailsCommande();
        existingDetail.setDetailId(1L); // Changed from setId to setDetailId
        existingDetail.setQty(5);

        when(detailCommandeRepository.findById(1L)).thenReturn(Optional.of(existingDetail));

        mockMvc.perform(MockMvcRequestBuilders.put("/detailCommande/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"qty\": 10}"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Détail de commande modifié avec succès"));
    }

    @Test
    public void testModifierDetailCommandeNotFound() throws Exception {
        when(detailCommandeRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.put("/detailCommande/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"qty\": 10}"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Détail de commande non trouvé"));
    }

    @Test
    public void testSupprimerDetailCommande() throws Exception {
        when(detailCommandeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(detailCommandeRepository).deleteById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/detailCommande/{id}", 1))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Détail de commande supprimé avec succès"));
    }
}
