package com.Mustapha.SpringBootDemo.services;

import com.Mustapha.SpringBootDemo.models.BarberModel;
import com.Mustapha.SpringBootDemo.repositories.BarberRepository;
import com.Mustapha.SpringBootDemo.responseModels.BarberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BarberService {

    @Autowired
    private BarberRepository barberRepository;

    public BarberService() {
    }

    public List<BarberResponse> getAllBarbers() throws Exception{
        List<BarberModel> barbers = barberRepository.findAll();
        List<BarberResponse> responseBarbers = new ArrayList<BarberResponse>();
        if (barbers.isEmpty()) {
            throw new Exception("No barber was found");
        }

        for (BarberModel barber : barbers ) {
            responseBarbers.add(new BarberResponse(barber.getId(),barber.getFirstName(),barber.getLastName(),barber.getDescription(),barber.getFullDescription()));
        }

        return responseBarbers;
    }

    public BarberResponse getBarberById(Long barberId) throws Exception{
        Optional<BarberModel> barber = barberRepository.findById(barberId);
        if (barber.isEmpty()) {
            throw new Exception("No barber was found");
        }
        BarberResponse responseBarber = new BarberResponse( barber.get().getId(),
                                                            barber.get().getFirstName(),
                                                            barber.get().getLastName(),
                                                            barber.get().getDescription(),
                                                            barber.get().getFullDescription());
        return responseBarber;
    }

}
