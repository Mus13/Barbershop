package com.Mustapha.SpringBootDemo.repositories;


import com.Mustapha.SpringBootDemo.models.ServiceModel;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
public class ServiceRepositoryTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ServiceRepository serviceRepository;

    @Test
    void retrieveServicesTest() {
        logger.info("\nServices -> {}",serviceRepository.retrieveServices());
    }

    @Test
    void findServiceByIdTest() {
        logger.info("\nService by id -> {}",serviceRepository.findServiceById(40001));
    }

    @Test
    @Transactional
    void save() {
        ServiceModel serviceModel= new ServiceModel("Combo","Combination of haircut and beard.","70 pln");
        serviceRepository.save(serviceModel);
        logger.info("\nService saved -> {}",serviceRepository.findServiceById(1));
    }

    @Test
    @Transactional
    void save_update() {
        ServiceModel serviceModel=serviceRepository.findServiceById(40001);
        serviceModel.setDescription(serviceModel.getDescription()+" updated!");
        serviceRepository.save(serviceModel);
        ServiceModel serviceModel_up=serviceRepository.findServiceById(40001);
        logger.info("\nService updated -> {}",serviceModel_up);
    }

    @Test
    @DirtiesContext
    @Transactional
    void removeTest() {
        serviceRepository.remove(serviceRepository.findServiceById(40002));
        logger.info("\n Services after delete -> {}",serviceRepository.retrieveServices());
    }
}
