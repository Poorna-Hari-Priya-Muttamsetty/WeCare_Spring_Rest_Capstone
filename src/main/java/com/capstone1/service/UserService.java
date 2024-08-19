package com.capstone1.service;

import com.capstone1.dto.AppointmentDTO;
import com.capstone1.dto.LifeCoachDTO;
import com.capstone1.dto.UserDTO;
import com.capstone1.exception.AppointmentNotFoundException;
import com.capstone1.exception.LifeCoachNotFoundException;
import com.capstone1.exception.UserNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

	UserDTO signUp(UserDTO userDTO);
	
	String login(String username, String password) throws UserNotFoundException;
	
	UserDTO getUserProfile(int userId) throws UserNotFoundException;
	
	List<LifeCoachDTO> getAllLifeCoaches();
	
	AppointmentDTO bookAppointment(AppointmentDTO appointmentDTO) throws UserNotFoundException, LifeCoachNotFoundException;
	
	List<AppointmentDTO> getAllUpcomingAppointments(int userId) throws UserNotFoundException;
	
	AppointmentDTO rescheduleAppointment(int appointmentId, LocalDateTime date) throws AppointmentNotFoundException;
	
	AppointmentDTO cancelAppointment(int appointmentId) throws AppointmentNotFoundException;
}
