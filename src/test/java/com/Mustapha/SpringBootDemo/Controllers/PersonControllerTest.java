package com.Mustapha.SpringBootDemo.Controllers;


import com.Mustapha.SpringBootDemo.Models.AppointmentModel;
import com.Mustapha.SpringBootDemo.Models.PersonModel;
import com.Mustapha.SpringBootDemo.Repositories.AppointmentRepository;
import com.Mustapha.SpringBootDemo.Repositories.PersonRepository;
import com.Mustapha.SpringBootDemo.Security.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PersonControllerTest {

    @Autowired
    @InjectMocks
    PersonController personController;

    @Mock
    private PersonRepository personRepository;
    @Mock
    private AppointmentRepository appointmentRepository;

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
        when(personRepository.findById(clientId)).thenReturn(personModel);
        doNothing().when(personRepository).remove(any(PersonModel.class));
        // Perform the DELETE request
        mockMvc.perform(delete("/api/clients/deleteClient/{id}", clientId))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteClientNotFound() throws Exception {
        long clientId = 1;

        // Mock that the client with the given ID does not exist
        when(personRepository.findById(clientId)).thenReturn(null);

        // Perform the DELETE request
        mockMvc.perform(delete("/api/clients/deleteClient/{id}", clientId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllClientsSuccess() throws Exception {
        List<PersonModel> clients;
        clients = new ArrayList<>();
        PersonModel personModel=new PersonModel("Ibrahim","Atta","Client from Biskra",new AppUser("Ibrahim_biskra","1234","Client"));
        clients.add(personModel);
        // Mock that the client with the given ID exists
        when(personRepository.retrieveAllByRole("Client")).thenReturn(clients);

        // Perform the GET request
        mockMvc.perform(get("/api/clients/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllClientsNotFound() throws Exception {
        List<PersonModel> clients= new ArrayList<>();

        // Mock that the client with the given ID does not exist
        when(personRepository.retrieveAllByRole("Client")).thenReturn(clients);

        // Perform the GET request
        mockMvc.perform(get("/api/clients/getAll"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetClientSuccess() throws Exception {
        long id=0;
        PersonModel personModel=new PersonModel("Ibrahim","Atta","Client from Biskra",new AppUser("Ibrahim_biskra","1234","Client"));

        // Mock that the client with the given ID exists
        when(personRepository.findById(anyLong())).thenReturn(personModel);

        // Perform the GET request
        mockMvc.perform(get("/api/clients/getClient/{id}",id))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetClientNotFound() throws Exception {

        long id=0;
        // Mock that the client with the given ID does not exist
        when(personRepository.findById(anyLong())).thenReturn(null);

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
        doNothing().when(personRepository).save(null);
        mockMvc.perform(post("/api/clients/saveClient")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(null)))
                .andExpect(status().isBadRequest());
        verify(personRepository, times(0)).save(any(PersonModel.class));
    }





    @Test
    public void testDeleteBarberSuccess() throws Exception {
        long barberId = 10004;
        PersonModel personModel=new PersonModel("Ibrahim","Atta","Barber from Biskra",new AppUser("Ibrahim_biskra","1234","Barber"));
        // Mock that the client with the given ID exists
        when(personRepository.findById(barberId)).thenReturn(personModel);
        doNothing().when(personRepository).remove(any(PersonModel.class));
        // Perform the DELETE request
        mockMvc.perform(delete("/api/barbers/deleteBarber/{id}", barberId))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBarberNotFound() throws Exception {
        long barberId = 1;

        // Mock that the client with the given ID does not exist
        when(personRepository.findById(barberId)).thenReturn(null);

        // Perform the DELETE request
        mockMvc.perform(delete("/api/barbers/deleteBarber/{id}", barberId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllBarbersSuccess() throws Exception {
        List<PersonModel> barbers=new ArrayList<>();
        PersonModel personModel=new PersonModel("Ibrahim","Atta","Barber from Biskra",new AppUser("Ibrahim_biskra","1234","Barber"));
        barbers.add(personModel);
        // Mock that the client with the given ID exists
        when(personRepository.retrieveAllByRole("Barber")).thenReturn(barbers);

        // Perform the GET request
        mockMvc.perform(get("/api/barbers/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllBarbersNotFound() throws Exception {
        List<PersonModel> barbers= new ArrayList<>();

        // Mock that the client with the given ID does not exist
        when(personRepository.retrieveAllByRole("Barber")).thenReturn(barbers);

        // Perform the GET request
        mockMvc.perform(get("/api/barbers/getAll"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetBarberSuccess() throws Exception {
        long id=0;
        PersonModel personModel=new PersonModel("Ibrahim","Atta","Barber from Biskra",new AppUser("Ibrahim_biskra","1234","Barber"));

        // Mock that the client with the given ID exists
        when(personRepository.findById(anyLong())).thenReturn(personModel);

        // Perform the GET request
        mockMvc.perform(get("/api/barbers/getBarber/{id}",id))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetBarberNotFound() throws Exception {

        long id=0;
        // Mock that the client with the given ID does not exist
        when(personRepository.findById(anyLong())).thenReturn(null);

        // Perform the GET request
        mockMvc.perform(get("/api/barbers/getBarber/{id}",id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSaveBarberSuccess() throws Exception {
        PersonModel personModel=new PersonModel("Ibrahim","Atta","Barber from Biskra",
                new AppUser("Ibrahim_biskra","1234","Barber"));

        doNothing().when(personRepository).save(personModel);
        // Perform the saveClient request
        mockMvc.perform(post("/api/barbers/saveBarber")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(personModel)))
                .andExpect(status().isOk());
        verify(personRepository, times(1)).save(any(PersonModel.class));
    }

    @Test
    public void testGetAppointmentsByClientIdSuccess() throws Exception {
        List<AppointmentModel> appointments= new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.NOVEMBER, 5);
        Date date = new Date(calendar.getTime().getTime());
        Time firstTime = Time.valueOf("11:30:00");
        Time secondTime = Time.valueOf("12:00:00");
        AppointmentModel appointment = new AppointmentModel(date, firstTime, secondTime);
        appointments.add(appointment);
        when(appointmentRepository.findAppointmentsByClientId(anyLong())).thenReturn(appointments);
        // Perform the saveClient request
        mockMvc.perform(get("/api/clients/{id}/appointments",1))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAppointmentsByClientIdFailure() throws Exception {
        List<AppointmentModel> appointments= new ArrayList<>();
        when(appointmentRepository.findAppointmentsByClientId(anyLong())).thenReturn(appointments);
        // Perform the saveClient request
        mockMvc.perform(get("/api/clients/{id}/appointments",1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSaveBarberFailure() throws Exception {
        doNothing().when(personRepository).save(null);
        // Perform the saveClient request
        mockMvc.perform(post("/api/barbers/saveBarber")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(null)))
                .andExpect(status().isBadRequest());
        verify(personRepository, times(0)).save(any(PersonModel.class));
    }

    @Test
    public void testGetAppointmentsByBarberIdSuccess() throws Exception {
        List<AppointmentModel> appointments= new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.NOVEMBER, 5);
        Date date = new Date(calendar.getTime().getTime());
        Time firstTime = Time.valueOf("11:30:00");
        Time secondTime = Time.valueOf("12:00:00");
        AppointmentModel appointment = new AppointmentModel(date, firstTime, secondTime);
        appointments.add(appointment);
        when(appointmentRepository.findAppointmentsByBarberId(anyLong())).thenReturn(appointments);
        // Perform the saveClient request
        mockMvc.perform(get("/api/barbers/{id}/appointments",1))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAppointmentsByBarberIdFailure() throws Exception {
        List<AppointmentModel> appointments= new ArrayList<>();
        when(appointmentRepository.findAppointmentsByBarberId(anyLong())).thenReturn(appointments);
        // Perform the saveClient request
        mockMvc.perform(get("/api/barbers/{id}/appointments",1))
                .andExpect(status().isNotFound());
    }
}