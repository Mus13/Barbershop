package com.Mustapha.SpringBootDemo.Repositories;

import com.Mustapha.SpringBootDemo.Models.ProductModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<ProductModel, Long>, CrudRepository<ProductModel,Long> {
    List<ProductModel> findAll();
    ProductModel save(ProductModel product);
    void delete(ProductModel product);
}