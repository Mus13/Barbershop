package com.Mustapha.SpringBootDemo.Repositories;

import com.Mustapha.SpringBootDemo.Models.ServiceModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;
import java.util.List;

public interface ServiceRepository extends PagingAndSortingRepository<ServiceModel, Long>, CrudRepository<ServiceModel,Long> {
    default List<ServiceModel> findAll(){
        List<ServiceModel> services = new ArrayList<>();
        ServiceModel serviceModel= new ServiceModel();
        serviceModel.setId(1);
        serviceModel.setName("HairCut");
        serviceModel.setPrice("50 pln");
        serviceModel.setDescription("The hair cut includes washing the hair and giving suggestions for hairstyles that could properly fit specifically each client.");
        services.add(serviceModel);
        ServiceModel secondServiceModel= new ServiceModel();
        secondServiceModel.setId(2);
        secondServiceModel.setName("Beard");
        secondServiceModel.setPrice("30 pln");
        secondServiceModel.setDescription("The beard is faded and trimmed based on the client's request.");
        services.add(secondServiceModel);
        return services;
    }
    ServiceModel save(ServiceModel service);
    void delete(ServiceModel service);
}
