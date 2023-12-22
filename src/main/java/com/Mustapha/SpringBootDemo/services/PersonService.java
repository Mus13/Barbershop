package com.Mustapha.SpringBootDemo.services;

import com.Mustapha.SpringBootDemo.models.PersonModel;
import com.Mustapha.SpringBootDemo.repositories.PersonRepository;
import com.Mustapha.SpringBootDemo.responseModels.PersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonService() {
    }

    public List<PersonResponse> getAllBarbers() throws Exception{
        List<PersonModel> barbers = personRepository.retrieveAllByRole("Barber");
        List<PersonResponse> responseBarbers = new ArrayList<PersonResponse>();
        if (barbers.isEmpty()) {
            throw new Exception("No barber was found");
        }

        for (PersonModel barber : barbers ) {
            responseBarbers.add(new PersonResponse(barber.getId(),barber.getFirstName(),barber.getLastName(),barber.getDescription()));
        }

        return responseBarbers;
    }

}
