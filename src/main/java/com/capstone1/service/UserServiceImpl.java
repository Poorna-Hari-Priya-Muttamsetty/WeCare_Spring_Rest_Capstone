package com.capstone1.service;

import com.capstone1.dto.AppointmentDTO;
import com.capstone1.dto.LifeCoachDTO;
import com.capstone1.dto.UserDTO;
import com.capstone1.entity.Appointment;
import com.capstone1.entity.LifeCoach;
import com.capstone1.entity.User;
import com.capstone1.exception.AppointmentNotFoundException;
import com.capstone1.exception.LifeCoachNotFoundException;
import com.capstone1.exception.UserNotFoundException;
import com.capstone1.repository.AppointmentRepository;
import com.capstone1.repository.LifeCoachRepository;
import com.capstone1.repository.UserRepository;
import com.capstone1.util.AppointmentConstants;
import com.capstone1.util.LifeCoachConstants;
import com.capstone1.util.UserConstants;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("ValidationMessages.properties")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LifeCoachRepository lifeCoachRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Environment environment;

	@Override
	public UserDTO signUp(UserDTO userDTO) {
		userRepository.saveAndFlush(modelMapper.map(userDTO,User.class));
		return userDTO;
	}

	@Override
	public String login(String username, String password) throws UserNotFoundException {
		User user = userRepository.findByUserName(username);
		if(user != null) {
			if(user.getPassword().equals(password))
				return environment.getProperty(UserConstants.USER_LOGIN_SUCCESS.toString());
			else
				return environment.getProperty(UserConstants.USER_LOGIN_FAILED.toString());
		} else {
			throw new UserNotFoundException(environment.getProperty(UserConstants.USER_NOT_FOUND.toString()));
		}
	}

	@Override
	public UserDTO getUserProfile(int userId) throws UserNotFoundException {
		Optional<User> userOp = userRepository.findById(userId);
		if(userOp.isPresent()) {
			return modelMapper.map(userOp.get(), UserDTO.class);
		} else {
			throw new UserNotFoundException(environment.getProperty(UserConstants.USER_NOT_FOUND.toString()));
		}	
	}

	@Override
	public List<LifeCoachDTO> getAllLifeCoaches() {
		List<LifeCoach> lifeCoach = lifeCoachRepository.findAll();
		List<LifeCoachDTO> lifeCoaches = new ArrayList<>();
		for(LifeCoach lc : lifeCoach){
			lifeCoaches.add(modelMapper.map(lc,LifeCoachDTO.class));
		}
		return lifeCoaches;
	}

	@Override
	public AppointmentDTO bookAppointment(AppointmentDTO appointmentDTO) throws UserNotFoundException, LifeCoachNotFoundException {
		Optional<User> userOp = userRepository.findById(appointmentDTO.getUserId());
		User user = null;
		if(userOp.isPresent()) {
			user = userOp.get();
		} else {
			throw new UserNotFoundException(environment.getProperty(UserConstants.USER_NOT_FOUND.toString()));
		}

		Optional<LifeCoach> lifeCoachOp = lifeCoachRepository.findById(appointmentDTO.getLifeCoachId());
		LifeCoach coach = null;
		if(lifeCoachOp.isPresent()) {
			coach = lifeCoachOp.get();
		} else {
			throw new LifeCoachNotFoundException(environment.getProperty(LifeCoachConstants.LIFECOACH_NOT_FOUND.toString()));
		}

		appointmentRepository.save(modelMapper.map(appointmentDTO,Appointment.class));
		return appointmentDTO;
	}

	@Override
	public List<AppointmentDTO> getAllUpcomingAppointments(int userId) throws UserNotFoundException {
		Optional<User> userOp = userRepository.findById(userId);
		User user = null;
		List<AppointmentDTO> appointmentDTOs = new ArrayList<>();
		if(userOp.isPresent()) {
			user = userOp.get();
			List<Appointment> appointments = appointmentRepository.findAllUpcomingAppointmentsForUser(userId);
			for(Appointment app : appointments) {
				appointmentDTOs.add(modelMapper.map(app,AppointmentDTO.class));
			}
			return appointmentDTOs;
		} else {
			throw new UserNotFoundException(environment.getProperty(UserConstants.USER_NOT_FOUND.toString()));
		}
	}

	@Override
	public AppointmentDTO rescheduleAppointment(int appointmentId, LocalDateTime date) throws AppointmentNotFoundException {
		Optional<Appointment> appointmentOp = appointmentRepository.findById(appointmentId);
		Appointment apppointment = null;
		if(appointmentOp.isPresent()) {
			apppointment = appointmentOp.get();
			apppointment.setAppointmentDate(date);
			appointmentRepository.saveAndFlush(modelMapper.map(apppointment,Appointment.class));
			return modelMapper.map(apppointment, AppointmentDTO.class);
		} else {
			throw new AppointmentNotFoundException(environment.getProperty(AppointmentConstants.APPPOINTMENT_NOT_FOUND.toString()));
		}
	}

	@Override
	public AppointmentDTO cancelAppointment(int appointmentId) throws AppointmentNotFoundException{
		Optional<Appointment> appointmentOp = appointmentRepository.findById(appointmentId);
		Appointment apppointment = null;
		if(appointmentOp.isPresent()) {
			apppointment = appointmentOp.get();
			appointmentRepository.delete(modelMapper.map(apppointment,Appointment.class));
			return modelMapper.map(apppointment, AppointmentDTO.class);
		} else {
			throw new AppointmentNotFoundException(environment.getProperty(AppointmentConstants.APPPOINTMENT_NOT_FOUND.toString()));
		}	
	}
}
