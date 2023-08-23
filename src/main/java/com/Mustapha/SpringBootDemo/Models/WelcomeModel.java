package com.Mustapha.SpringBootDemo.Models;

import org.springframework.stereotype.Component;

@Component
public class WelcomeModel {

    private final String nameSalon="Low-Key Barbershop";
    private String[] names ={"Mustapha Belabed", "Daria Katarzyna Karwacka"} ;
    private  String[] jobs={"Mustapha: Barber","Daria: Fryzjera"};
    private  String[] descriptions={"Mustapha: mam 29 lat jestem wesoly, sympatyczny i ma fach w reku.","Daria: mam 23 lata jestem sympatyczna, mila i pozytywna ucyka jak ma czancja."};

    public String[] names() {
        return names;
    }

    public String getNameSalon(){
        return nameSalon;
    }

    public String[] getJobs(){
        return jobs;
    }

    public String[] getDescriptions(){
        return descriptions;
    }
}
