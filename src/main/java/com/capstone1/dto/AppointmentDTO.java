package com.capstone1.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AppointmentDTO {
	
	private int id;
	
	@NotNull(message = "{appointment.userid.must}")
    private int userId;
	
	@NotNull(message = "{appointment.coachid.must}")
	private int lifeCoachId;
	
	@FutureOrPresent(message = "{appointment.date.valid}")
	private LocalDateTime appointmentDate;
	
	@Min(message = "{appointment.duration.check}", value = 30)
	private int duration;

	public AppointmentDTO(int id,int userId,int lifeCoachId,LocalDateTime appointmentDate,int duration) {
		super();
		this.id = id;
		this.userId = userId;
		this.lifeCoachId = lifeCoachId;
		this.appointmentDate = appointmentDate;
		this.duration = duration;
	}

	public AppointmentDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotNull(message = "{appointment.userid.must}")
	public int getUserId() {
		return userId;
	}

	public void setUserId(@NotNull(message = "{appointment.userid.must}") int userId) {
		this.userId = userId;
	}

	@NotNull(message = "{appointment.coachid.must}")
	public int getLifeCoachId() {
		return lifeCoachId;
	}

	public void setLifeCoachId(@NotNull(message = "{appointment.coachid.must}") int lifeCoachId) {
		this.lifeCoachId = lifeCoachId;
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
