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
public class ServiceControllerTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @InjectMocks
    ServiceController serviceController;

    @Mock
    private ServiceRepository serviceRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(serviceController).build();
    }

    @Test
    public void testDeleteServiceSuccess() throws Exception {
        long serviceId = 40001;
        ServiceModel serviceModel=new ServiceModel("Combo","Combination of haircut and beard.","70 pln");
        // Mock that the service with the given ID exists
        when(serviceRepository.findServiceById(serviceId)).thenReturn(serviceModel);
        doNothing().when(serviceRepository).remove(any(ServiceModel.class));
        // Perform the DELETE request
        mockMvc.perform(delete("/api/services/deleteService/{id}", serviceId))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteServiceNotFound() throws Exception {
        long serviceId = 1;

        // Mock that the service with the given ID does not exist
        when(serviceRepository.findServiceById(serviceId)).thenReturn(null);

        // Perform the DELETE request
        mockMvc.perform(delete("/api/services/deleteService/{id}", serviceId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllServicesSuccess() throws Exception {
        List<ServiceModel> services=new ArrayList<ServiceModel>();
        ServiceModel serviceModel=new ServiceModel("Combo","Combination of haircut and beard.","70 pln");
        services.add(serviceModel);
        // Mock that the service with the given ID exists
        when(serviceRepository.retrieveServices()).thenReturn(services);

        // Perform the GET request
        mockMvc.perform(get("/api/services/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllServicesNotFound() throws Exception {
        List<ServiceModel> services=new ArrayList<ServiceModel>();

        // Mock that the service with the given ID does not exist
        when(serviceRepository.retrieveServices()).thenReturn(services);

        // Perform the GET request
        mockMvc.perform(get("/api/services/getAll"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetServiceSuccess() throws Exception {
        long id=0;
        ServiceModel serviceModel=new ServiceModel("Combo","Combination of haircut and beard.","70 pln");

        // Mock that the service with the given ID exists
        when(serviceRepository.findServiceById(anyLong())).thenReturn(serviceModel);

        // Perform the GET request
        mockMvc.perform(get("/api/services/getService/{id}",id))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetServiceNotFound() throws Exception {

        long id=0;
        // Mock that the service with the given ID does not exist
        when(serviceRepository.findServiceById(anyLong())).thenReturn(null);

        // Perform the GET request
        mockMvc.perform(get("/api/services/getService/{id}",id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSaveServiceSuccess() throws Exception {
        ServiceModel serviceModel=new ServiceModel("Combo","Combination of haircut and beard.","70 pln");

        doNothing().when(serviceRepository).save(serviceModel);
        // Perform the saveService request
        mockMvc.perform(post("/api/services/saveService")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(serviceModel)))
                .andExpect(status().isOk());
        verify(serviceRepository, times(1)).save(any(ServiceModel.class));
    }

    @Test
    public void testSaveServiceFailure() throws Exception {
        // Prepare a null service model
        ServiceModel serviceModel = null;

        doNothing().when(serviceRepository).save(serviceModel);
        // Perform the saveService request
        mockMvc.perform(post("/api/services/saveService")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(serviceModel)))
                .andExpect(status().isBadRequest());
        verify(serviceRepository, times(0)).save(any(ServiceModel.class));
    }

}