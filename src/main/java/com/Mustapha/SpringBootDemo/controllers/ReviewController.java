package com.Mustapha.SpringBootDemo.controllers;

import com.Mustapha.SpringBootDemo.models.ReviewModel;
import com.Mustapha.SpringBootDemo.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<ReviewModel>> getAllReviews() {
        // Retrieve the list of clients from the repository
        List<ReviewModel> reviews = reviewRepository.retrieveReviews();
        if (reviews.isEmpty()) {
            // If no products are found, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        } else {
            // If products are found, return a 200 OK response with the list of clients
            return ResponseEntity.ok(reviews);
        }
    }

    @GetMapping("/getReview/{id}")
    public ResponseEntity<ReviewModel> getReviewById(@PathVariable long id) {
        // Retrieve the list of clients from the repository
        ReviewModel review = reviewRepository.findReviewById(id);
        if (review==null) {
            // If no client is found, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(review);
        }
    }

    @PostMapping("/saveReview")
    public ResponseEntity<String> saveReview(@RequestBody ReviewModel review) {
        if (review != null) {
            // Save the person object using the repository
            reviewRepository.save(review);
            return ResponseEntity.ok("Review saved successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid review data");
        }
    }

    @DeleteMapping("/deleteReview/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable long id) {
        ReviewModel review=reviewRepository.findReviewById(id);
        // Check if the client with the given ID exists
        if (review!=null) {
            reviewRepository.remove(review);
            return ResponseEntity.ok("Review with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
