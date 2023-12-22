package com.Mustapha.SpringBootDemo.repositories;

import com.Mustapha.SpringBootDemo.models.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository  extends JpaRepository<AppointmentModel,Long> {
    @Query("SELECT a FROM AppointmentModel a WHERE a.barber.id = :barberId")
    List<AppointmentModel> findAppointmentsByBarberId(@Param("barberId") Long barberId);

    @Query("SELECT a FROM AppointmentModel a WHERE a.client.id = :clientId")
    List<AppointmentModel> findAppointmentsByClientId(@Param("clientId") Long clientId);
}