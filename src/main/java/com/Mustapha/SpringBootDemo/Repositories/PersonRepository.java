package com.Mustapha.SpringBootDemo.Repositories;

import com.Mustapha.SpringBootDemo.Models.PersonModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PersonRepository extends PagingAndSortingRepository<PersonModel, Long>, CrudRepository<PersonModel, Long> {
    List<PersonModel> findAll();
    PersonModel save(PersonModel person);
    void delete(PersonModel person);

}
