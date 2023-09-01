package com.Mustapha.SpringBootDemo.Controllers;

import com.Mustapha.SpringBootDemo.Models.ProductModel;
import com.Mustapha.SpringBootDemo.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/private/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/addProduct")
    public void addProduct(@RequestBody ProductModel product) {
        productRepository.save(product);
    }

    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@RequestBody ProductModel product) {
        productRepository.delete(product);
    }

    @PreAuthorize("hasAnyRole('OWNER')")
    @PutMapping("/updateProduct")
    public void updateProduct(@RequestBody ProductModel product) {
        productRepository.update(product);
    }
}
