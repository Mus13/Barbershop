package com.Mustapha.SpringBootDemo.Repositories;

import com.Mustapha.SpringBootDemo.Models.PersonModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class PersonRepository{

    @Autowired
    EntityManager entityManager;

    public List<PersonModel> retrieveAllByRole(String role){
        TypedQuery<PersonModel> query = entityManager.createQuery(
                "SELECT cl FROM PersonModel cl WHERE cl.appUser.role = :role",
                PersonModel.class
        );

        query.setParameter("role", role);

        List<PersonModel> results = query.getResultList();
        return results;
    }

    public PersonModel findById(long id){
        return entityManager.find(PersonModel.class,id);
    }

    public void save(PersonModel person){
        if ( 0 == person.getId()){
            entityManager.persist(person);
            entityManager.persist(person.getAppUser());
            entityManager.flush();
        }else{
            entityManager.merge(person);
            entityManager.flush();
        }

    }

    public void remove(PersonModel person){
        entityManager.remove(person);
    }
}
