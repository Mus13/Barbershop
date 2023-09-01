package com.Mustapha.SpringBootDemo.Models;

import org.springframework.stereotype.Component;

@Component
public class SalonModel {

    private final String nameSalon = "Low-Key Barbershop";
    private String address = "Kollataja 5/U3 20-060 Lublin";
    private String description = "Our salon welcomes Africans and strives to provide clean cuts to our valued customers.";

    public String getNameSalon() {
        return nameSalon;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }
}
