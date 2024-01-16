package com.Mustapha.SpringBootDemo.repositories;

import com.Mustapha.SpringBootDemo.models.BarberModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberRepository extends JpaRepository<BarberModel,Long> {

}
