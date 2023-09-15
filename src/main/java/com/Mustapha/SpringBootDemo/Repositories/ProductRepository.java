package com.Mustapha.SpringBootDemo.Repositories;

import com.Mustapha.SpringBootDemo.Models.ProductModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;
import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<ProductModel, Long>, CrudRepository<ProductModel,Long> {
    default List<ProductModel> findAll(){
        List<ProductModel> products = new ArrayList<>();
        ProductModel productModel= new ProductModel();
        productModel.setId(1);
        productModel.setName("Sea Salt Spray");
        productModel.setInstructions("Spray on wet hair and let for few minutes for the hair to curl up.");
        productModel.setDescription("Sea Salt Spray nurtures the hair with the vitamins and proteins inside the spray bottle.");
        products.add(productModel);
        ProductModel secondProductModel= new ProductModel();
        secondProductModel.setId(2);
        secondProductModel.setName("Beard Oil");
        secondProductModel.setInstructions("Put small amount in hands and gently rub the beard for a minutes.");
        secondProductModel.setDescription("The beard oil nurtures the beard and give it a shine and soft touch.");
        products.add(secondProductModel);
        return products;
    }
    ProductModel save(ProductModel product);
    void delete(ProductModel product);
}