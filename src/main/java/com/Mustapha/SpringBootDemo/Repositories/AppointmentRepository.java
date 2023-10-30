package com.Mustapha.SpringBootDemo.Repositories;

import com.Mustapha.SpringBootDemo.Models.AppointmentModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class AppointmentRepository {

    @Autowired
    EntityManager entityManager;

    public List<AppointmentModel> retrieveAppointments(){
        return entityManager.createQuery("Select a From AppointmentModel a", AppointmentModel.class).getResultList();
    }

    public AppointmentModel findAppointmentById(long id){
        return entityManager.find(AppointmentModel.class,id);
    }

    public List<AppointmentModel> findAppointmentsByBarberId(long barberId){
        TypedQuery<AppointmentModel> query = entityManager.createQuery(
                "SELECT a FROM AppointmentModel a WHERE a.barber.id = :barberId",
                AppointmentModel.class
        );
        query.setParameter("barberId", barberId);

        List<AppointmentModel> appointments = query.getResultList();

        if (!appointments.isEmpty()) {
            return appointments;
        } else {
            return null; // No appointments found for the specified barberId.
        }
    }

    public List<AppointmentModel> findAppointmentsByClientId(long clientId){
        TypedQuery<AppointmentModel> query = entityManager.createQuery(
                "SELECT a FROM AppointmentModel a WHERE a.client.id = :clientId",
                AppointmentModel.class
        );
        query.setParameter("clientId", clientId);

        List<AppointmentModel> appointments = query.getResultList();

        if (!appointments.isEmpty()) {
            return appointments; // Assuming only one appointment is associated with a barber.
        } else {
            return null; // No appointments found for the specified barberId.
        }
    }

    public void save(AppointmentModel appointmentModel){
        if ( 0 == appointmentModel.getId()){
            entityManager.persist(appointmentModel);
            entityManager.flush();
        }else{
            entityManager.merge(appointmentModel);
            entityManager.flush();
        }
    }

    public void remove(AppointmentModel appointmentModel){
        entityManager.remove(appointmentModel);
    }
}