package com.Mustapha.SpringBootDemo.Repositories;

import com.Mustapha.SpringBootDemo.Models.PersonModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends PersonRepository{

    @Override
    default List<PersonModel> findAll() {
        return null;
    }

    @Override
    default PersonModel save(PersonModel person) {
        return null;
    }

    @Override
    default void delete(PersonModel person) {}

}
