package com.ecom.ecomshop.controllersTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ecom.ecomshop.controller.TransactionController;
import com.ecom.ecomshop.model.Transaction;
import com.ecom.ecomshop.repository.TransactionRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    public void testListeTransactions() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setTransactionDate(new Date()); // Use current date for simplicity
        transaction.setTransactionStatus("SUCCESS");

        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction));

        mockMvc.perform(MockMvcRequestBuilders.get("/transaction"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionId").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionStatus").value("SUCCESS"));
    }

    @Test
    public void testAjouterTransaction() throws Exception {
        Transaction newTransaction = new Transaction();
        newTransaction.setTransactionStatus("PENDING");

        mockMvc.perform(MockMvcRequestBuilders.post("/ajouterTransaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"transactionStatus\":\"PENDING\"}"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Transaction ajoutée avec succès"));

        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    public void testModifierTransactionFound() throws Exception {
        Transaction existingTransaction = new Transaction();
        existingTransaction.setTransactionId(1L);
        existingTransaction.setTransactionStatus("PENDING");

        when(transactionRepository.findById(1L)).thenReturn(Optional.of(existingTransaction));

        mockMvc.perform(MockMvcRequestBuilders.put("/transaction/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"transactionStatus\":\"SUCCESS\"}"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Transaction modifiée avec succès"));
    }

    @Test
    public void testModifierTransactionNotFound() throws Exception {
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.put("/transaction/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"transactionStatus\":\"SUCCESS\"}"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Transaction non trouvée"));
    }

    @Test
    public void testSupprimerTransaction() throws Exception {
        when(transactionRepository.existsById(1L)).thenReturn(true);
        doNothing().when(transactionRepository).deleteById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/transaction/1"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Transaction supprimée avec succès"));
    }
}