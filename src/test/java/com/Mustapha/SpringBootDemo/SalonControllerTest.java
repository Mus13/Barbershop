package com.Mustapha.SpringBootDemo;

import com.Mustapha.SpringBootDemo.Controllers.SalonController;
import com.Mustapha.SpringBootDemo.Models.PersonModel;
import com.Mustapha.SpringBootDemo.Models.ProductModel;
import com.Mustapha.SpringBootDemo.Models.SalonModel;
import com.Mustapha.SpringBootDemo.Models.ServiceModel;
import com.Mustapha.SpringBootDemo.Repositories.ProductRepository;
import com.Mustapha.SpringBootDemo.Repositories.ServiceRepository;
import com.Mustapha.SpringBootDemo.Repositories.StaffRepository;
import com.Mustapha.SpringBootDemo.Services.SalonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SalonControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private SalonController salonController;

    @Mock
    private SalonService salonService;

    @Mock
    private StaffRepository staffRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(salonController).build();
    }

    @Test
    public void testGetSalonInfo() throws Exception {

        SalonModel salonModel = new SalonModel();
        salonModel.setAddress("Kollataja 5/U3 20-060 Lublin");
        salonModel.setDescription("Our salon welcomes Africans and strives to provide clean cuts to our valued customers.");

        when(salonService.getSalonInfo()).thenReturn(salonModel);
        mockMvc.perform(get("/api/public/salon")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameSalon").value(salonModel.getNameSalon()))
                .andExpect(jsonPath("$.address").value(salonModel.getAddress()))
                .andExpect(jsonPath("$.description").value(salonModel.getDescription()));
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        List<PersonModel> employees = new ArrayList<>();
        PersonModel personModel= new PersonModel();
        personModel.setId(1);
        personModel.setFirstName("Gift Gerald");
        personModel.setLastName("Moyo");
        personModel.setDescription("Gift is a gifted barber from Zimbabwe.");
        employees.add(personModel);
        PersonModel secondPersonModel= new PersonModel();
        secondPersonModel.setId(2);
        secondPersonModel.setFirstName("Ayoub");
        secondPersonModel.setLastName("Kroudi");
        secondPersonModel.setDescription("Ayoub is a talented Barber from Morocco.");
        employees.add(secondPersonModel);

        when(staffRepository.findAll()).thenReturn(employees);
        mockMvc.perform(get("/api/public/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1)) // Assuming the first employee has ID 1
                .andExpect(jsonPath("$[0].firstName").value("Gift Gerald"))
                .andExpect(jsonPath("$[0].lastName").value("Moyo"))
                .andExpect(jsonPath("$[0].description").value("Gift is a gifted barber from Zimbabwe."))
                .andExpect(jsonPath("$[1].id").value(2)) // Assuming the second employee has ID 2
                .andExpect(jsonPath("$[1].firstName").value("Ayoub"))
                .andExpect(jsonPath("$[1].lastName").value("Kroudi"))
                .andExpect(jsonPath("$[1].description").value("Ayoub is a talented Barber from Morocco."));

    }

    @Test
    public void testGetAllServices() throws Exception {

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

        when(serviceRepository.findAll()).thenReturn(services);
        mockMvc.perform(get("/api/public/services")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1)) // Assuming the first employee has ID 1
                .andExpect(jsonPath("$[0].name").value("HairCut"))
                .andExpect(jsonPath("$[0].price").value("50 pln"))
                .andExpect(jsonPath("$[0].description").value("The hair cut includes washing the hair and giving suggestions for hairstyles that could properly fit specifically each client."))
                .andExpect(jsonPath("$[1].id").value(2)) // Assuming the second employee has ID 2
                .andExpect(jsonPath("$[1].name").value("Beard"))
                .andExpect(jsonPath("$[1].price").value("30 pln"))
                .andExpect(jsonPath("$[1].description").value("The beard is faded and trimmed based on the client's request."));
    }

    @Test
    public void testGetAllProducts() throws Exception {

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

        when(productRepository.findAll()).thenReturn(products);
        mockMvc.perform(get("/api/public/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1)) // Assuming the first employee has ID 1
                .andExpect(jsonPath("$[0].name").value("Sea Salt Spray"))
                .andExpect(jsonPath("$[0].instructions").value("Spray on wet hair and let for few minutes for the hair to curl up."))
                .andExpect(jsonPath("$[0].description").value("Sea Salt Spray nurtures the hair with the vitamins and proteins inside the spray bottle."))
                .andExpect(jsonPath("$[1].id").value(2)) // Assuming the second employee has ID 2
                .andExpect(jsonPath("$[1].name").value("Beard Oil"))
                .andExpect(jsonPath("$[1].instructions").value("Put small amount in hands and gently rub the beard for a minutes."))
                .andExpect(jsonPath("$[1].description").value("The beard oil nurtures the beard and give it a shine and soft touch."));
    }
}
