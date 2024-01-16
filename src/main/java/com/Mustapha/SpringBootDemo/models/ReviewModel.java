package com.Mustapha.SpringBootDemo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ReviewModel {

    @Id
    @GeneratedValue
    private long id;
    private long rating;
    private String description;
    @OneToOne
    private AppointmentModel appointmentModel;

    public ReviewModel() {
    }

    public ReviewModel(long rating, String description) {
        this.rating = rating;
        this.description = description;
    }
}
