package com.Mustapha.SpringBootDemo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class ReviewModel {

    @Id
    @GeneratedValue
    private long id;
    private String rating;
    private String description;
    @OneToOne
    private AppointmentModel appointmentModel;

    public ReviewModel() {
    }

    public ReviewModel(String rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AppointmentModel getAppointmentModel() {
        return appointmentModel;
    }

    public void setAppointmentModel(AppointmentModel appointmentModel) {
        this.appointmentModel = appointmentModel;
    }

    @Override
    public String toString() {
        return "ReviewModel{" +
                "id=" + id +
                ", rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                ", appointment='" + appointmentModel + '\'' +
                '}';
    }
}
