package com.ecom.ecomshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecomshop.model.Transaction;
import com.ecom.ecomshop.repository.TransactionRepository;

@RestController
public class TransactionController {
    
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/transaction")
    public List<Transaction> transactions() {
        return transactionRepository.findAll();
    }
}
