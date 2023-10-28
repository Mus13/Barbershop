package com.Mustapha.SpringBootDemo.Controllers;

import com.Mustapha.SpringBootDemo.Models.ProductModel;
import com.Mustapha.SpringBootDemo.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        // Retrieve the list of clients from the repository
        List<ProductModel> products = productRepository.retrieveProducts();
        if (products.isEmpty()) {
            // If no products are found, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        } else {
            // If products are found, return a 200 OK response with the list of clients
            return ResponseEntity.ok(products);
        }
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable long id) {
        // Retrieve the list of clients from the repository
        ProductModel product = productRepository.findProductById(id);
        if (product==null) {
            // If no client is found, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(product);
        }
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<String> saveProduct(@RequestBody ProductModel product) {
        if (product != null) {
            // Save the person object using the repository
            productRepository.save(product);
            return ResponseEntity.ok("Product saved successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid product data");
        }
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        ProductModel productModel=productRepository.findProductById(id);
        // Check if the client with the given ID exists
        if (productModel!=null) {
            productRepository.remove(productModel);
            return ResponseEntity.ok("Product with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if the client doesn't exist
        }
    }
}
