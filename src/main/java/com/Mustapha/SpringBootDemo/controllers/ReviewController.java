package com.Mustapha.SpringBootDemo.controllers;

import com.Mustapha.SpringBootDemo.responseModels.ReviewResponse;
import com.Mustapha.SpringBootDemo.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/barber/{id}")
    public ResponseEntity<List<ReviewResponse>> getAllReviewsByBarberId(@PathVariable Long id) throws Exception {
        // Retrieve the list of reviews for the specified barberId
        List<ReviewResponse> reviews = reviewService.getAllReviewsByBarberId(id);
        return ResponseEntity.ok(reviews);
    }

}
