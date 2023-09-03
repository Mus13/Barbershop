package com.Mustapha.SpringBootDemo.Repositories;

import com.Mustapha.SpringBootDemo.Models.ServiceModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ServiceRepository extends PagingAndSortingRepository<ServiceModel, Long>, CrudRepository<ServiceModel,Long> {
    List<ServiceModel> findAll();
    ServiceModel save(ServiceModel service);
    void delete(ServiceModel service);
    ServiceModel updateService(ServiceModel updatedService);
}
