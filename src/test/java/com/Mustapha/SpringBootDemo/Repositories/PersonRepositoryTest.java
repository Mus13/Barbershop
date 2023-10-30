package com.Mustapha.SpringBootDemo.Repositories;


import com.Mustapha.SpringBootDemo.Models.PersonModel;
import com.Mustapha.SpringBootDemo.Repositories.PersonRepository;
import com.Mustapha.SpringBootDemo.Security.AppUser;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
public class PersonRepositoryTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonRepository personRepository;

    @Test
    void retrieveClientsTest() {
        logger.info("\nClients -> {}",personRepository.retrieveAllByRole("Client"));
    }

    @Test
    void retrieveBarbersTest() {
        logger.info("\nBarbers -> {}",personRepository.retrieveAllByRole("Barber"));
    }

    @Test
    void retrieveManagersTest() {
        logger.info("\nManagers -> {}",personRepository.retrieveAllByRole("Manager"));
    }

    @Test
    void findPersonByIdTest() {
        logger.info("\nPerson by id -> {}",personRepository.findById(10001));
    }

    @Test
    @Transactional
    void save() {
        PersonModel person_model=new PersonModel("Ibrahim","Atta","Client from Biskra",new AppUser("Ibrahim_biskra","1234","Client"));
        personRepository.save(person_model);
        logger.info("\nPerson saved -> {}",personRepository.findById(1));
    }

    @Test
    @Transactional
    void save_update_person() {
        PersonModel person_model=personRepository.findById(10001);
        person_model.setDescription(person_model.getDescription()+" updated!");
        personRepository.save(person_model);
        PersonModel person_model_up=personRepository.findById(10001);
        logger.info("\nClient updated -> {}",person_model_up);
    }

    @Test
    @Transactional
    void save_update_app_user() {
        PersonModel person_model=personRepository.findById(10001);
        person_model.getAppUser().setUsername(person_model.getAppUser().getUsername()+"_updated");
        personRepository.save(person_model);
        PersonModel person_model_up=personRepository.findById(10001);
        logger.info("\nApp_User updated -> {}",person_model_up);
    }

    @Test
    @DirtiesContext
    @Transactional
    void removeTest() {
        personRepository.remove(personRepository.findById(10002));
        logger.info("\n Clients after delete -> {}",personRepository.retrieveAllByRole("Client"));
    }
}
