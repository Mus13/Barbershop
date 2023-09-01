package com.Mustapha.SpringBootDemo.Controllers;

import com.Mustapha.SpringBootDemo.Models.PersonModel;
import com.Mustapha.SpringBootDemo.Models.ProductModel;
import com.Mustapha.SpringBootDemo.Models.SalonModel;
import com.Mustapha.SpringBootDemo.Models.ServiceModel;
import com.Mustapha.SpringBootDemo.Repositories.ProductRepository;
import com.Mustapha.SpringBootDemo.Repositories.ServiceRepository;
import com.Mustapha.SpringBootDemo.Repositories.StaffRepository;
import com.Mustapha.SpringBootDemo.Services.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class SalonController {
    @Autowired
    private SalonService salonService;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/salon")
    public SalonModel getSalonInfo(){
        return salonService.getSalonInfo();
    }

    @GetMapping("/employees")
    public List<PersonModel> getAllEmployees() {
        return staffRepository.findAll();
    }

    @GetMapping("/services")
    public List<ServiceModel> getAllServices() {
        return serviceRepository.findAll();
    }

    @GetMapping("/products")
    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

}
