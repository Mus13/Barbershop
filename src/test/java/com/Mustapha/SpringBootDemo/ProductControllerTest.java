package com.Mustapha.SpringBootDemo;


import com.Mustapha.SpringBootDemo.Controllers.ProductController;
import com.Mustapha.SpringBootDemo.Models.ProductModel;
import com.Mustapha.SpringBootDemo.Repositories.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ProductControllerTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @InjectMocks
    ProductController productController;

    @Mock
    private ProductRepository productRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testDeleteProductSuccess() throws Exception {
        long productId = 30001;
        ProductModel productModel=new ProductModel("LisaP Spray","Spray with Keratine to help straightening the hair.","Spray on hair and gently massage the hair and let rest for few minutes.");
        // Mock that the product with the given ID exists
        when(productRepository.findProductById(productId)).thenReturn(productModel);
        doNothing().when(productRepository).remove(any(ProductModel.class));
        // Perform the DELETE request
        mockMvc.perform(delete("/api/products/deleteProduct/{id}", productId))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteProductNotFound() throws Exception {
        long productId = 1;

        // Mock that the product with the given ID does not exist
        when(productRepository.findProductById(productId)).thenReturn(null);

        // Perform the DELETE request
        mockMvc.perform(delete("/api/products/deleteProduct/{id}", productId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllProductsSuccess() throws Exception {
        List<ProductModel> products=new ArrayList<ProductModel>();
        ProductModel productModel=new ProductModel("LisaP Spray","Spray with Keratine to help straightening the hair.","Spray on hair and gently massage the hair and let rest for few minutes.");
        products.add(productModel);
        // Mock that the product with the given ID exists
        when(productRepository.retrieveProducts()).thenReturn(products);

        // Perform the GET request
        mockMvc.perform(get("/api/products/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllProductsNotFound() throws Exception {
        List<ProductModel> products=new ArrayList<ProductModel>();

        // Mock that the product with the given ID does not exist
        when(productRepository.retrieveProducts()).thenReturn(products);

        // Perform the GET request
        mockMvc.perform(get("/api/products/getAll"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetProductSuccess() throws Exception {
        long id=0;
        ProductModel productModel=new ProductModel("LisaP Spray","Spray with Keratine to help straightening the hair.","Spray on hair and gently massage the hair and let rest for few minutes.");

        // Mock that the product with the given ID exists
        when(productRepository.findProductById(anyLong())).thenReturn(productModel);

        // Perform the GET request
        mockMvc.perform(get("/api/products/getProduct/{id}",id))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProductNotFound() throws Exception {

        long id=0;
        // Mock that the product with the given ID does not exist
        when(productRepository.findProductById(anyLong())).thenReturn(null);

        // Perform the GET request
        mockMvc.perform(get("/api/products/getProduct/{id}",id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSaveProductSuccess() throws Exception {
        ProductModel productModel=new ProductModel("LisaP Spray","Spray with Keratine to help straightening the hair.","Spray on hair and gently massage the hair and let rest for few minutes.");

        doNothing().when(productRepository).save(productModel);
        // Perform the saveProduct request
        mockMvc.perform(post("/api/products/saveProduct")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(productModel)))
                .andExpect(status().isOk());
        verify(productRepository, times(1)).save(any(ProductModel.class));
    }

    @Test
    public void testSaveProductFailure() throws Exception {
        // Prepare a null product model
        ProductModel productModel = null;

        doNothing().when(productRepository).save(productModel);
        // Perform the saveProduct request
        mockMvc.perform(post("/api/products/saveProduct")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(productModel)))
                .andExpect(status().isBadRequest());
        verify(productRepository, times(0)).save(any(ProductModel.class));
    }

}