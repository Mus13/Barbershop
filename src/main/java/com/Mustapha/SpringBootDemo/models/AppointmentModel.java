package com.Mustapha.SpringBootDemo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Entity
@Data
public class AppointmentModel {

    @Id
    @GeneratedValue
    private long id;
    private Date date_appointment;
    private Time time_start;
    private Time time_end;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;
    @ManyToOne
    @JoinColumn(name = "barber_id")
    private BarberModel barber;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceModel service;

    public AppointmentModel() {
    }

    public AppointmentModel(Date date, Time time_start, Time time_end) {
        this.date_appointment = date;
        this.time_start = time_start;
        this.time_end = time_end;
    }

}
