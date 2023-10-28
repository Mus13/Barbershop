package com.Mustapha.SpringBootDemo.Repositories;

import com.Mustapha.SpringBootDemo.Models.ServiceModel;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ServiceRepository{

    @Autowired
    EntityManager entityManager;
    
    public List<ServiceModel> retrieveServices(){
        return  entityManager.createQuery("Select s From ServiceModel s", ServiceModel.class).getResultList();
    }

    public ServiceModel findServiceById(long id){
        return entityManager.find(ServiceModel.class,id);
    }

    public void save(ServiceModel serviceModel){
        if ( 0 == serviceModel.getId()){
            entityManager.persist(serviceModel);
            entityManager.flush();
        }else{
            entityManager.merge(serviceModel);
            entityManager.flush();
        }

    }

    public void remove(ServiceModel service){
        entityManager.remove(service);
    }
}
