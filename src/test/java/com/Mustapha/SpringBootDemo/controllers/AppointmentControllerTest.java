package com.Mustapha.SpringBootDemo.controllers;

import com.Mustapha.SpringBootDemo.models.AppointmentModel;
import com.Mustapha.SpringBootDemo.repositories.AppointmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
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
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AppointmentControllerTest {

    @Autowired
    @InjectMocks
    AppointmentController appointmentController;

    @Mock
    private AppointmentRepository appointmentRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }

    @Test
    void getAllAppointmentSuccess() throws Exception {
        List<AppointmentModel> appointmentModels=new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.NOVEMBER, 5);
        Date date = new Date(calendar.getTime().getTime());
        Time firstTime = Time.valueOf("11:30:00");
        Time secondTime = Time.valueOf("12:00:00");
        AppointmentModel appointment = new AppointmentModel(date, firstTime, secondTime);
        appointmentModels.add(appointment);
        when(appointmentRepository.findAll()).thenReturn(appointmentModels);
        // Perform the GET request
        mockMvc.perform(get("/api/appointments/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllAppointmentFailure() throws Exception {
        List<AppointmentModel> appointmentModels=new ArrayList<>();
        when(appointmentRepository.findAll()).thenReturn(appointmentModels);
        // Perform the GET request
        mockMvc.perform(get("/api/appointments/getAll"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAppointmentByIdSuccess() throws Exception {
        long appointmentId=1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.NOVEMBER, 5);
        Date date = new Date(calendar.getTime().getTime());
        Time firstTime = Time.valueOf("11:30:00");
        Time secondTime = Time.valueOf("12:00:00");
        AppointmentModel appointment = new AppointmentModel(date, firstTime, secondTime);
        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));
        // Perform the GET request
        mockMvc.perform(get("/api/appointments/getAppointment/{id}",appointmentId))
                .andExpect(status().isOk());
    }

    @Test
    void getAppointmentByIdFailure() throws Exception {
        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        // Perform the GET request
        mockMvc.perform(get("/api/appointments/getAppointment/{id}",1))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void saveAppointmentSuccess() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.NOVEMBER, 5);
        Date date = new Date(calendar.getTime().getTime());
        Time firstTime = Time.valueOf("11:30:00");
        Time secondTime = Time.valueOf("12:00:00");
        AppointmentModel appointment = new AppointmentModel(date, firstTime, secondTime);
        // Perform the saveClient request
        mockMvc.perform(post("/api/appointments/saveAppointment")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(appointment)))
                .andExpect(status().isOk());
        verify(appointmentRepository, times(1)).save(any(AppointmentModel.class));
    }

    @Test
    void saveAppointmentFailure() throws Exception {
        // Perform the saveClient request
        mockMvc.perform(post("/api/appointments/saveAppointment")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(null)))
                .andExpect(status().isBadRequest());
        verify(appointmentRepository, times(0)).save(any(AppointmentModel.class));
    }

    @Test
    void deleteAppointmentSuccess() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.NOVEMBER, 5);
        Date date = new Date(calendar.getTime().getTime());
        Time firstTime = Time.valueOf("11:30:00");
        Time secondTime = Time.valueOf("12:00:00");
        AppointmentModel appointment = new AppointmentModel(date, firstTime, secondTime);
        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));
        doNothing().when(appointmentRepository).delete(appointment);
        // Perform the saveClient request
        mockMvc.perform(delete("/api/appointments/deleteAppointment/{id}",1))
                .andExpect(status().isOk());
        verify(appointmentRepository, times(1)).findById(anyLong());
        verify(appointmentRepository, times(1)).delete(any(AppointmentModel.class));
    }

    @Test
    void deleteAppointmentFailure() throws Exception {
        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        doNothing().when(appointmentRepository).delete(any(AppointmentModel.class));
        // Perform the saveClient request
        mockMvc.perform(delete("/api/appointments/deleteAppointment/{id}",1L))
                .andExpect(status().isNotFound());
        verify(appointmentRepository, times(1)).findById(anyLong());
        verify(appointmentRepository, times(0)).delete(any(AppointmentModel.class));
    }
}