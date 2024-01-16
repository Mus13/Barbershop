package com.Mustapha.SpringBootDemo.services;

import com.Mustapha.SpringBootDemo.models.ReviewModel;
import com.Mustapha.SpringBootDemo.repositories.ReviewRepository;
import com.Mustapha.SpringBootDemo.responseModels.ReviewResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewService() {
    }

    public List<ReviewResponse> getAllReviewsByBarberId(Long barberId){

        List<ReviewModel> reviews = reviewRepository.findByAppointmentModel_Barber_Id(barberId);
        List<ReviewResponse> responseReviews = new ArrayList<ReviewResponse>();


        for (ReviewModel review : reviews ) {
            responseReviews.add(new ReviewResponse( review.getId(),
                                                    review.getRating(),
                                                    barberId,
                                                    review.getDescription(),
                                                    review.getAppointmentModel().getDate_appointment().toString(),
                                                    review.getAppointmentModel().getClient().getFirstName()+" "+review.getAppointmentModel().getClient().getLastName()));
        }

        return responseReviews;
    }
}
