package com.Mustapha.SpringBootDemo.repositories;

import com.Mustapha.SpringBootDemo.models.AppointmentModel;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class AppointmentRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    ServiceRepository serviceRepository;

    @Test
    void retrieveAppointments() {
        logger.info("\nAppointments -> {}",appointmentRepository.findAll());
    }

    @Test
    void findAppointmentById() {
        logger.info("\nAppointment 50001 -> {}",appointmentRepository.findById(50001L).get());
    }

    @Test
    void findAppointmentsByBarberId() {
        logger.info("\nAppointments by barber ID 10004 -> {}",appointmentRepository.findAppointmentsByBarberId(10004L));
    }

    @Test
    void findAppointmentsByClientId() {
        logger.info("\nAppointments by client ID 10001 -> {}",appointmentRepository.findAppointmentsByClientId(10001L));
    }

    @Test
    @Transactional
    void save() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.NOVEMBER, 5);

        Date date = new Date(calendar.getTime().getTime());
        Time firstTime = Time.valueOf("11:30:00");
        Time secondTime = Time.valueOf("12:00:00");

        AppointmentModel appointment = new AppointmentModel(date, firstTime, secondTime);
        appointment.setBarber(personRepository.findById(10004));
        appointment.setClient(personRepository.findById(10003));
        appointment.setService(serviceRepository.findServiceById(40001));
        appointmentRepository.save(appointment);
        logger.info("\nAppointments after saving -> {}",appointmentRepository.findAll());
    }

    @Test
    @Transactional
    void save_update() {
        AppointmentModel appointment=appointmentRepository.findById(50001L).get();
        appointment.setBarber(personRepository.findById(10005));
        appointmentRepository.save(appointment);
        logger.info("\nAppointments after update -> {}",appointmentRepository.findAll());
    }

    @Test
    @Transactional
    void remove() {
        appointmentRepository.delete(appointmentRepository.findById(50001L).get());
        logger.info("\nAppointment after delete -> {}",appointmentRepository.findAll());
    }
}