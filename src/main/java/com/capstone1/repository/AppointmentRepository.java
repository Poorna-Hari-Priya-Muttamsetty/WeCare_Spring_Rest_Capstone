package com.capstone1.repository;

import com.capstone1.entity.Appointment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate > CURRENT_TIMESTAMP AND a.user.id = ?1")
    List<Appointment> findAllUpcomingAppointmentsForUser(int userId);

    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate > CURRENT_TIMESTAMP AND a.lifeCoach.id = ?1")
    List<Appointment> findAllUpcomingAppointmentsForLifeCoach(int lifeCoachId);

}
