package com.Mustapha.SpringBootDemo.responseModels;

import lombok.Data;

@Data
public class ReviewResponse {
    private long id;
    private long rating;
    private long barberId;
    private String description;
    private String date;
    private String userName;

    public ReviewResponse(long id, long rating, long barberId, String description, String date, String userName) {
        this.id = id;
        this.rating = rating;
        this.barberId = barberId;
        this.description = description;
        this.date = date;
        this.userName = userName;
    }
}
