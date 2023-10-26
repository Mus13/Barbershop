package com.Mustapha.SpringBootDemo.Repositories;

import com.Mustapha.SpringBootDemo.Models.PersonModel;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class PersonRepository{

    @Autowired
    EntityManager entityManager;

    public List<PersonModel> retrieveClients(){
        List<PersonModel> clients=entityManager.createQuery("Select cl From PersonModel where cl.appUser.role = 'Client'", PersonModel.class).getResultList();
        return clients;
    }

    public PersonModel findClientById(long id){
        return entityManager.find(PersonModel.class,id);
    }
    public void save(PersonModel person){
        entityManager.merge(person);
    }

    public void remove(PersonModel person){
        entityManager.remove(person);
    }
}
