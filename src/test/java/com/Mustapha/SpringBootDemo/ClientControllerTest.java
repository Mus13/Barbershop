package com.Mustapha.SpringBootDemo;

import com.Mustapha.SpringBootDemo.Controllers.ClientController;
import com.Mustapha.SpringBootDemo.Models.PersonModel;
import com.Mustapha.SpringBootDemo.Repositories.ClientRepository;
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

public class ClientControllerTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientController clientController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    @WithMockUser(roles = "ROLE_CLIENT")
    public void testSaveClient() throws Exception {
        PersonModel person = new PersonModel();
        // Set person properties as needed
        person.setId(4);
        person.setFirstName("Achraf");
        person.setLastName("Hakimi");
        person.setDescription("This client likes low taper with curly hair on top.");


        // Create an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/api/private/clients/saveClient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person))) // Convert object to JSON
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ROLE_CLIENT")
    public void testDeleteClient() throws Exception {
        PersonModel person = new PersonModel();
        // Set person properties as needed
        person.setId(4);
        person.setFirstName("Achraf");
        person.setLastName("Hakimi");
        person.setDescription("This client likes low taper with curly hair on top.");


        // Create an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        String personJson = objectMapper.writeValueAsString(person);

        mockMvc.perform(delete("/api/private/clients/deleteClient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person))) // Convert object to JSON
                .andExpect(status().isOk());
    }
}
