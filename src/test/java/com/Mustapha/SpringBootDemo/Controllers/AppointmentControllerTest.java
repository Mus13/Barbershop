package com.Mustapha.SpringBootDemo.Controllers;

import com.Mustapha.SpringBootDemo.Models.AppointmentModel;
import com.Mustapha.SpringBootDemo.Repositories.AppointmentRepository;
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
        when(appointmentRepository.retrieveAppointments()).thenReturn(appointmentModels);
        // Perform the GET request
        mockMvc.perform(get("/api/appointments/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllAppointmentFailure() throws Exception {
        List<AppointmentModel> appointmentModels=new ArrayList<>();
        when(appointmentRepository.retrieveAppointments()).thenReturn(appointmentModels);
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
        when(appointmentRepository.findAppointmentById(appointmentId)).thenReturn(appointment);
        // Perform the GET request
        mockMvc.perform(get("/api/appointments/getAppointment/{id}",appointmentId))
                .andExpect(status().isOk());
    }

    @Test
    void getAppointmentByIdFailure() throws Exception {
        when(appointmentRepository.findAppointmentById(anyLong())).thenReturn(null);
        // Perform the GET request
        mockMvc.perform(get("/api/appointments/getAppointment/{id}",1))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveAppointmentSuccess() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.NOVEMBER, 5);
        Date date = new Date(calendar.getTime().getTime());
        Time firstTime = Time.valueOf("11:30:00");
        Time secondTime = Time.valueOf("12:00:00");
        AppointmentModel appointment = new AppointmentModel(date, firstTime, secondTime);
        doNothing().when(appointmentRepository).save(appointment);
        // Perform the saveClient request
        mockMvc.perform(post("/api/appointments/saveAppointment")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(appointment)))
                .andExpect(status().isOk());
        verify(appointmentRepository, times(1)).save(any(AppointmentModel.class));
    }

    @Test
    void saveAppointmentFailure() throws Exception {
        doNothing().when(appointmentRepository).save(null);
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
        when(appointmentRepository.findAppointmentById(anyLong())).thenReturn(appointment);
        doNothing().when(appointmentRepository).remove(appointment);
        // Perform the saveClient request
        mockMvc.perform(delete("/api/appointments/deleteAppointment/{id}",1))
                .andExpect(status().isOk());
        verify(appointmentRepository, times(1)).findAppointmentById(anyLong());
        verify(appointmentRepository, times(1)).remove(any(AppointmentModel.class));
    }

    @Test
    void deleteAppointmentFailure() throws Exception {
        when(appointmentRepository.findAppointmentById(anyLong())).thenReturn(null);
        doNothing().when(appointmentRepository).remove(null);
        // Perform the saveClient request
        mockMvc.perform(delete("/api/appointments/deleteAppointment/{id}",1))
                .andExpect(status().isNotFound());
        verify(appointmentRepository, times(1)).findAppointmentById(anyLong());
        verify(appointmentRepository, times(0)).remove(any(AppointmentModel.class));
    }
}