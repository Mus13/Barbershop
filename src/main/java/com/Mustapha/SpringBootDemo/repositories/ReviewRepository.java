package com.Mustapha.SpringBootDemo.repositories;

import com.Mustapha.SpringBootDemo.models.ReviewModel;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ReviewRepository {

    @Autowired
    EntityManager entityManager;

    public List<ReviewModel> retrieveReviews(){
        return entityManager.createQuery("Select r From ReviewModel r", ReviewModel.class).getResultList();
    }

    public ReviewModel findReviewById(long id){
        return entityManager.find(ReviewModel.class,id);
    }

    public void save(ReviewModel reviewModel){
        if ( 0 == reviewModel.getId()){
            entityManager.persist(reviewModel);
            entityManager.flush();
        }else{
            entityManager.merge(reviewModel);
            entityManager.flush();
        }

    }

    public void remove(ReviewModel reviewModel){
        entityManager.remove(reviewModel);
    }
}