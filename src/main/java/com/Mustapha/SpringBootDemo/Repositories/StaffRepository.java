package com.Mustapha.SpringBootDemo.Repositories;

import com.Mustapha.SpringBootDemo.Models.PersonModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface StaffRepository  extends PersonRepository {

    @Override
    default List<PersonModel> findAll() {
        List<PersonModel> employees = new ArrayList<>();
        PersonModel personModel= new PersonModel();
        personModel.setId(1);
        personModel.setFirstName("Gift Gerald");
        personModel.setLastName("Moyo");
        personModel.setDescription("Gift is a gifted barber from Zimbabwe.");
        personModel.setAppUser(null);
        employees.add(personModel);
        PersonModel secondPersonModel= new PersonModel();
        secondPersonModel.setId(2);
        secondPersonModel.setFirstName("Ayoub");
        secondPersonModel.setLastName("Kroudi");
        secondPersonModel.setDescription("Ayoub is a talented Barber from Morocco.");
        secondPersonModel.setAppUser(null);
        employees.add(secondPersonModel);
        return employees;
    }

    @Override
    default PersonModel save(PersonModel person) {
        return null;
    }

    @Override
    default void delete(PersonModel person) {

    }

}
