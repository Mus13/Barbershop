package com.Mustapha.SpringBootDemo.repositories;

import com.Mustapha.SpringBootDemo.models.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewModel,Long> {

    List<ReviewModel> findByAppointmentModel_Barber_Id(Long barberId);
}