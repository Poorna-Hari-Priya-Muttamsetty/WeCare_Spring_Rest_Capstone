package com.capstone1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "life_coach_id")
    private LifeCoach lifeCoach;

	@Column(name = "appointment_time")
	private LocalDateTime appointmentDate;
    
    private int duration;

	public Appointment(int id, User user, LifeCoach lifeCoach, LocalDateTime appointmentDate, int duration) {
		super();
		this.id = id;
		this.user = user;
		this.lifeCoach = lifeCoach;
		this.appointmentDate = appointmentDate;
		this.duration = duration;
	}

	public Appointment() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LifeCoach getLifeCoach() {
		return lifeCoach;
	}

	public void setLifeCoach(LifeCoach lifeCoach) {
		this.lifeCoach = lifeCoach;
	}

	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDateTime appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
    
}
