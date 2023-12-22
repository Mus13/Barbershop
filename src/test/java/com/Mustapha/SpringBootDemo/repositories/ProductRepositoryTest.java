package com.Mustapha.SpringBootDemo.repositories;


import com.Mustapha.SpringBootDemo.models.ProductModel;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
public class ProductRepositoryTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProductRepository productRepository;

    @Test
    void retrieveProductsTest() {
        logger.info("\nProducts -> {}",productRepository.retrieveProducts());
    }

    @Test
    void findProductByIdTest() {
        logger.info("\nProduct by id -> {}",productRepository.findProductById(30001));
    }

    @Test
    @Transactional
    void save() {
        ProductModel productModel= new ProductModel("LisaP Spray","Spray with Keratine to help straightening the hair.","Spray on hair and gently massage the hair and let rest for few minutes.");
        productRepository.save(productModel);
        logger.info("\nProduct saved -> {}",productRepository.findProductById(1));
    }

    @Test
    @Transactional
    void save_update() {
        ProductModel productModel=productRepository.findProductById(30001);
        productModel.setDescription(productModel.getDescription()+" updated!");
        productRepository.save(productModel);
        ProductModel productModel_up=productRepository.findProductById(30001);
        logger.info("\nProduct updated -> {}",productModel_up);
    }

    @Test
    @DirtiesContext
    @Transactional
    void removeTest() {
        productRepository.remove(productRepository.findProductById(30002));
        logger.info("\n Products after delete -> {}",productRepository.retrieveProducts());
    }
}
