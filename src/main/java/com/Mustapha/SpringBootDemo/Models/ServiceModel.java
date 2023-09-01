package com.Mustapha.SpringBootDemo.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String Description;
    private String price;
    @ManyToMany(mappedBy = "services")
    private List<PersonModel> staff = new ArrayList<>();

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @ManyToMany
    @JoinTable(
            name = "service_products",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductModel> products = new ArrayList<>();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setStaff(List<PersonModel> staff) {
        this.staff = staff;
    }

    public List<PersonModel> getStaff() {
        return staff;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }

    public void addStaff(PersonModel person) {
        staff.add(person);
    }

    public void removeStaff(PersonModel person) {
        staff.remove(person);
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void addProduct(ProductModel product) {
        products.add(product);
        product.addService(this);
    }

    public void removeProduct(ProductModel product) {
        products.remove(product);
        product.removeService(this);
    }
}
