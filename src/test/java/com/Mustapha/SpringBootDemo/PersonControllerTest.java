package com.Mustapha.SpringBootDemo;


import com.Mustapha.SpringBootDemo.Controllers.PersonController;
import com.Mustapha.SpringBootDemo.Models.PersonModel;
import com.Mustapha.SpringBootDemo.Repositories.PersonRepository;
import com.Mustapha.SpringBootDemo.Security.AppUser;
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
public class PersonControllerTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @InjectMocks
    PersonController personController;

    @Mock
    private PersonRepository personRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    public void testDeleteClientSuccess() throws Exception {
        long clientId = 10001;
        PersonModel personModel=new PersonModel("Ibrahim","Atta","Client from Biskra",new AppUser("Ibrahim_biskra","1234","Client"));
        // Mock that the client with the given ID exists
        when(personRepository.findClientById(clientId)).thenReturn(personModel);
        doNothing().when(personRepository).remove(any(PersonModel.class));
        // Perform the DELETE request
        mockMvc.perform(delete("/api/clients/deleteClient/{id}", clientId))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteClientNotFound() throws Exception {
        long clientId = 1;

        // Mock that the client with the given ID does not exist
        when(personRepository.findClientById(clientId)).thenReturn(null);

        // Perform the DELETE request
        mockMvc.perform(delete("/api/clients/deleteClient/{id}", clientId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllClientsSuccess() throws Exception {
        List<PersonModel> clients=new ArrayList<PersonModel>();
        PersonModel personModel=new PersonModel("Ibrahim","Atta","Client from Biskra",new AppUser("Ibrahim_biskra","1234","Client"));
        clients.add(personModel);
        // Mock that the client with the given ID exists
        when(personRepository.retrieveClients()).thenReturn(clients);

        // Perform the GET request
        mockMvc.perform(get("/api/clients/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllClientsNotFound() throws Exception {
        List<PersonModel> clients=new ArrayList<PersonModel>();

        // Mock that the client with the given ID does not exist
        when(personRepository.retrieveClients()).thenReturn(clients);

        // Perform the GET request
        mockMvc.perform(get("/api/clients/getAll"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetClientSuccess() throws Exception {
        long id=0;
        PersonModel personModel=new PersonModel("Ibrahim","Atta","Client from Biskra",new AppUser("Ibrahim_biskra","1234","Client"));

        // Mock that the client with the given ID exists
        when(personRepository.findClientById(anyLong())).thenReturn(personModel);

        // Perform the GET request
        mockMvc.perform(get("/api/clients/getClient/{id}",id))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetClientNotFound() throws Exception {

        long id=0;
        // Mock that the client with the given ID does not exist
        when(personRepository.findClientById(anyLong())).thenReturn(null);

        // Perform the GET request
        mockMvc.perform(get("/api/clients/getClient/{id}",id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSaveClientSuccess() throws Exception {
        PersonModel personModel=new PersonModel("Ibrahim","Atta","Client from Biskra",
                                                new AppUser("Ibrahim_biskra","1234","Client"));

        doNothing().when(personRepository).save(personModel);
        // Perform the saveClient request
        mockMvc.perform(post("/api/clients/saveClient")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(personModel)))
                .andExpect(status().isOk());
        verify(personRepository, times(1)).save(any(PersonModel.class));
    }

    @Test
    public void testSaveClientFailure() throws Exception {
        // Prepare a null person model
        PersonModel personModel = null;

        doNothing().when(personRepository).save(personModel);
        // Perform the saveClient request
        mockMvc.perform(post("/api/clients/saveClient")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(personModel)))
                .andExpect(status().isBadRequest());
        verify(personRepository, times(0)).save(any(PersonModel.class));
    }

}