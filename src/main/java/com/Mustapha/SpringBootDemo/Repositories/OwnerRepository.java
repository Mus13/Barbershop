package com.Mustapha.SpringBootDemo.Repositories;

import com.Mustapha.SpringBootDemo.Models.PersonModel;
import com.Mustapha.SpringBootDemo.Security.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository  extends PersonRepository {

    default Optional<PersonModel> findByRole(Role role) {
        return null;
    }

    @Override
    default PersonModel update(PersonModel updatedPerson) {
        return null;
    }
}
