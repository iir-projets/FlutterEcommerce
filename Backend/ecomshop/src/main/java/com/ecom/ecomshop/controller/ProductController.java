package com.ecom.ecomshop.controller;

import com.ecom.ecomshop.model.Product;
import com.ecom.ecomshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/testConnection")
    public List<Product> testConnection() {
        return productRepository.findAll();
    }
}
