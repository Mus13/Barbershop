package com.Mustapha.SpringBootDemo.Repositories;

import com.Mustapha.SpringBootDemo.Models.PersonModel;
import com.Mustapha.SpringBootDemo.Models.ServiceModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository  extends PersonRepository {

    @Override
    default PersonModel save(PersonModel person) {
        return null;
    }

    @Override
    default PersonModel update(PersonModel updatedPerson) {
        return null;
    }

    default void addService(PersonModel staff,ServiceModel service){

    }

    default void deleteService(PersonModel staff,ServiceModel service){

    }

    @Override
    default List<PersonModel> findAll() {
        return null;
    }

    @Override
    default void delete(PersonModel person) {

    }

}
