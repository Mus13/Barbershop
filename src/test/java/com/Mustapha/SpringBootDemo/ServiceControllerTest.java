package com.Mustapha.SpringBootDemo;

import com.Mustapha.SpringBootDemo.Controllers.ServiceController;
import com.Mustapha.SpringBootDemo.Models.ServiceModel;
import com.Mustapha.SpringBootDemo.Repositories.ServiceRepository;
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

public class ServiceControllerTest {

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceController serviceController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(serviceController).build();
    }

    @Test
    @WithMockUser(roles = "ROLE_OWNER")
    public void testSaveService() throws Exception {
        ServiceModel serviceModel= new ServiceModel();
        serviceModel.setId(1);
        serviceModel.setName("HairCut");
        serviceModel.setPrice("50 pln");
        serviceModel.setDescription("The hair cut includes washing the hair and giving suggestions for hairstyles that could properly fit specifically each client.");
        // Create an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/private/services/saveService")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(serviceModel))) // Convert object to JSON
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ROLE_OWNER")
    public void testDeleteService() throws Exception {
        ServiceModel serviceModel= new ServiceModel();
        serviceModel.setId(1);
        serviceModel.setName("HairCut");
        serviceModel.setPrice("50 pln");
        serviceModel.setDescription("The hair cut includes washing the hair and giving suggestions for hairstyles that could properly fit specifically each client.");
        // Create an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(delete("/api/private/services/deleteService")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(serviceModel))) // Convert object to JSON
                .andExpect(status().isOk());
    }
}
