package com.Mustapha.SpringBootDemo.Models;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
public class AppointmentModel {

    @Id
    @GeneratedValue
    private long id;
    private Date date_appointment;
    private Time time_start;
    private Time time_end;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private PersonModel client;
    @ManyToOne
    @JoinColumn(name = "barber_id")
    private PersonModel barber;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceModel service;
    @OneToOne(mappedBy = "appointmentModel")
    private ReviewModel review;

    public AppointmentModel() {
    }

    public AppointmentModel(Date date, Time time_start, Time time_end) {
        this.date_appointment = date;
        this.time_start = time_start;
        this.time_end = time_end;
    }

    public Date getDate_appointment() {
        return date_appointment;
    }

    public void setDate_appointment(Date date_appointment) {
        this.date_appointment = date_appointment;
    }

    public Date getDate() {
        return date_appointment;
    }

    public void setDate(Date date) {
        this.date_appointment = date;
    }

    public Time getTime_start() {
        return time_start;
    }

    public void setTime_start(Time time_start) {
        this.time_start = time_start;
    }

    public Time getTime_end() {
        return time_end;
    }

    public void setTime_end(Time time_end) {
        this.time_end = time_end;
    }

    public PersonModel getClient() {
        return client;
    }

    public void setClient(PersonModel client) {
        this.client = client;
    }

    public PersonModel getBarber() {
        return barber;
    }

    public void setBarber(PersonModel barber) {
        this.barber = barber;
    }

    public ReviewModel getReview() {
        return review;
    }

    public void setReview(ReviewModel review) {
        this.review = review;
    }

    public ServiceModel getService() {
        return service;
    }

    public void setService(ServiceModel service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "AppointmentModel{" +
                "date=" + date_appointment +
                ", time_start=" + time_start +
                ", time_end=" + time_end +
                ", client=" + client +
                ", barber=" + barber +
                ", service=" + service +
                (review!=null?", review=" + review:"")+
                '}';
    }
}
