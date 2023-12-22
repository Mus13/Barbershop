package com.Mustapha.SpringBootDemo.repositories;

import com.Mustapha.SpringBootDemo.models.ReviewModel;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReviewRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    AppointmentRepository appointmentRepository;

    @Test
    void retrieveReviews() {
        logger.info("\nReviews -> {}",reviewRepository.retrieveReviews());
    }

    @Test
    void findReviewById() {
        logger.info("\nReviews -> {}",reviewRepository.findReviewById(60001));
    }

    @Test
    @Transactional
    void save() {
        ReviewModel review=new ReviewModel("5","Awesome service!");
        review.setAppointmentModel(appointmentRepository.findById(50002L).get());
        reviewRepository.save(review);
        logger.info("\nReviews after saving -> {}",reviewRepository.retrieveReviews());
    }

    @Test
    @Transactional
    void save_update() {
        ReviewModel review=reviewRepository.findReviewById(60001);
        review.setDescription(review.getDescription()+" updated!");
        reviewRepository.save(review);
        logger.info("\nReviews after update -> {}",reviewRepository.retrieveReviews());
    }

    @Test
    @Transactional
    void remove() {
        reviewRepository.remove(reviewRepository.findReviewById(60001));
        logger.info("\nReviews after delete -> {}",reviewRepository.retrieveReviews());
    }
}