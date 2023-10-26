package com.Mustapha.SpringBootDemo.Controllers;

import com.Mustapha.SpringBootDemo.Models.ProductModel;
import com.Mustapha.SpringBootDemo.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/private/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/saveProduct")
    public void saveProduct(@RequestBody ProductModel product) {
        productRepository.save(product);
    }

    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@RequestBody ProductModel product) {
        productRepository.delete(product);
    }
}
