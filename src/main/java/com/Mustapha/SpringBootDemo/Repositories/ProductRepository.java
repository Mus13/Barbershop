package com.Mustapha.SpringBootDemo.Repositories;

import com.Mustapha.SpringBootDemo.Models.ProductModel;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ProductRepository{

    @Autowired
    EntityManager entityManager;

    public List<ProductModel> retrieveProducts(){
        return entityManager.createQuery("Select p From ProductModel p", ProductModel.class).getResultList();
    }

    public ProductModel findProductById(long id){
        return entityManager.find(ProductModel.class,id);
    }

    public void save(ProductModel productModel){
        if ( 0 == productModel.getId()){
            entityManager.persist(productModel);
            entityManager.flush();
        }else{
            entityManager.merge(productModel);
            entityManager.flush();
        }

    }

    public void remove(ProductModel product){
        entityManager.remove(product);
    }
}