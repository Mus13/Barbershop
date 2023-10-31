package com.Mustapha.SpringBootDemo.Controllers;

import com.Mustapha.SpringBootDemo.Models.ReviewModel;
import com.Mustapha.SpringBootDemo.Repositories.ReviewRepository;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ReviewControllerTest {

    @Autowired
    @InjectMocks
    ReviewController reviewController;

    @Mock
    private ReviewRepository reviewRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }

    @Test
    void getAllReviewsSuccess() throws Exception {
        List<ReviewModel> reviewModels=new ArrayList<>();
        ReviewModel reviewModel= new ReviewModel("5","Clean cuts");
        reviewModels.add(reviewModel);
        when(reviewRepository.retrieveReviews()).thenReturn(reviewModels);
        // Perform the GET request
        mockMvc.perform(get("/api/reviews/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllReviewsFailure() throws Exception {
        List<ReviewModel> reviewModels=new ArrayList<>();
        when(reviewRepository.retrieveReviews()).thenReturn(reviewModels);
        // Perform the GET request
        mockMvc.perform(get("/api/reviews/getAll"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getReviewByIdSuccess() throws Exception {
        long reviewId=1;
        ReviewModel reviewModel= new ReviewModel("5","Clean cuts");
        when(reviewRepository.findReviewById(reviewId)).thenReturn(reviewModel);
        // Perform the GET request
        mockMvc.perform(get("/api/reviews/getReview/{id}",reviewId))
                .andExpect(status().isOk());
    }

    @Test
    void getReviewByIdFailure() throws Exception {
        when(reviewRepository.findReviewById(anyLong())).thenReturn(null);
        // Perform the GET request
        mockMvc.perform(get("/api/reviews/getReview/{id}",1))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveReviewSuccess() throws Exception {
        ReviewModel reviewModel= new ReviewModel("5","Clean cuts");
        doNothing().when(reviewRepository).save(reviewModel);
        // Perform the saveClient request
        mockMvc.perform(post("/api/reviews/saveReview")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(reviewModel)))
                .andExpect(status().isOk());
        verify(reviewRepository, times(1)).save(any(ReviewModel.class));
    }

    @Test
    void saveReviewFailure() throws Exception {
        doNothing().when(reviewRepository).save(null);
        // Perform the saveClient request
        mockMvc.perform(post("/api/reviews/saveReview")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(null)))
                .andExpect(status().isBadRequest());
        verify(reviewRepository, times(0)).save(any(ReviewModel.class));
    }

    @Test
    void deleteReviewSuccess() throws Exception {
        ReviewModel reviewModel= new ReviewModel("5","Clean cuts");
        when(reviewRepository.findReviewById(anyLong())).thenReturn(reviewModel);
        doNothing().when(reviewRepository).remove(reviewModel);
        // Perform the saveClient request
        mockMvc.perform(delete("/api/reviews/deleteReview/{id}",1))
                .andExpect(status().isOk());
        verify(reviewRepository, times(1)).findReviewById(anyLong());
        verify(reviewRepository, times(1)).remove(any(ReviewModel.class));
    }

    @Test
    void deleteAppointmentFailure() throws Exception {
        when(reviewRepository.findReviewById(anyLong())).thenReturn(null);
        doNothing().when(reviewRepository).remove(null);
        // Perform the saveClient request
        mockMvc.perform(delete("/api/reviews/deleteReview/{id}",1))
                .andExpect(status().isNotFound());
        verify(reviewRepository, times(1)).findReviewById(anyLong());
        verify(reviewRepository, times(0)).remove(any(ReviewModel.class));
    }
}