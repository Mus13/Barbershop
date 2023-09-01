package com.Mustapha.SpringBootDemo.Services;

import com.Mustapha.SpringBootDemo.Models.SalonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalonService {

    @Autowired
    private SalonModel salonModel;

    public SalonModel getSalonInfo() {
        return salonModel;
    }

}
