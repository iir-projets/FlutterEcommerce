package com.ecom.ecomshop.controller;

import com.ecom.ecomshop.model.Transaction;
import com.ecom.ecomshop.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/transaction")
    public List<Transaction> listeTransactions() {
        return transactionRepository.findAll();
    }

    @PostMapping("/transaction")
    public String ajouterTransaction(@RequestBody Transaction transaction) {
        transactionRepository.save(transaction);
        return "Transaction ajoutée avec succès";
    }

    @PutMapping("/transaction/{id}")
    public String modifierTransaction(@PathVariable Long id, @RequestBody Transaction transactionModifie) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        if (transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            if (transactionModifie.getTransactionDate() != null) {
                transaction.setTransactionDate(transactionModifie.getTransactionDate());
            }
            if (transactionModifie.getTransactionStatus() != null) {
                transaction.setTransactionStatus(transactionModifie.getTransactionStatus());
            }
            // Vous pouvez ajouter des conditions similaires pour d'autres attributs si nécessaire
            transactionRepository.save(transaction);
            return "Transaction modifiée avec succès";
        } else {
            return "Transaction non trouvée";
        }
    }

    @DeleteMapping("/transaction/{id}")
    public String supprimerTransaction(@PathVariable Long id) {
        transactionRepository.deleteById(id);
        return "Transaction supprimée avec succès";
    }
}
