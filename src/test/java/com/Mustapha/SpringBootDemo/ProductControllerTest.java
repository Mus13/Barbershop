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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    @WithMockUser(roles = "ROLE_OWNER")
    public void testSaveProduct() throws Exception {
        ProductModel product = new ProductModel();
        ProductModel productModel= new ProductModel();
        productModel.setId(1);
        productModel.setName("Sea Salt Spray");
        productModel.setInstructions("Spray on wet hair and let for few minutes for the hair to curl up.");
        productModel.setDescription("Sea Salt Spray nurtures the hair with the vitamins and proteins inside the spray bottle.");
        // Create an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/private/products/saveProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product))) // Convert object to JSON
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ROLE_OWNER")
    public void testDeleteProduct() throws Exception {
        ProductModel productModel= new ProductModel();
        productModel.setId(1);
        productModel.setName("Sea Salt Spray");
        productModel.setInstructions("Spray on wet hair and let for few minutes for the hair to curl up.");
        productModel.setDescription("Sea Salt Spray nurtures the hair with the vitamins and proteins inside the spray bottle.");
        // Create an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(delete("/api/private/products/deleteProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productModel))) // Convert object to JSON
                .andExpect(status().isOk());
    }
}

