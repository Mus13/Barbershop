package com.Mustapha.SpringBootDemo;

import com.Mustapha.SpringBootDemo.Controllers.StaffController;
import com.Mustapha.SpringBootDemo.Models.PersonModel;
import com.Mustapha.SpringBootDemo.Repositories.StaffRepository;
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

public class StaffControllerTest {

    @Mock
    private StaffRepository staffRepository;

    @InjectMocks
    private StaffController staffController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(staffController).build();
    }

    @Test
    @WithMockUser(roles = "ROLE_OWNER")
    public void testSaveStaff() throws Exception {
        PersonModel personModel= new PersonModel();
        personModel.setId(2);
        personModel.setFirstName("Ayoub");
        personModel.setLastName("Kroudi");
        personModel.setDescription("Ayoub is a talented Barber from Morocco.");
        // Create an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/private/staff/saveStaff")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personModel))) // Convert object to JSON
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ROLE_OWNER")
    public void testDeleteStaff() throws Exception {
        PersonModel personModel= new PersonModel();
        personModel.setId(2);
        personModel.setFirstName("Ayoub");
        personModel.setLastName("Kroudi");
        personModel.setDescription("Ayoub is a talented Barber from Morocco.");
        // Create an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(delete("/api/private/staff/deleteStaff")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personModel))) // Convert object to JSON
                .andExpect(status().isOk());
    }
}
