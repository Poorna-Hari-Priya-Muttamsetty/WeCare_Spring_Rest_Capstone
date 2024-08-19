package com.capstone1.service;

import com.capstone1.dto.AppointmentDTO;
import com.capstone1.dto.LifeCoachDTO;
import com.capstone1.entity.Appointment;
import com.capstone1.entity.LifeCoach;
import com.capstone1.exception.LifeCoachNotFoundException;
import com.capstone1.repository.AppointmentRepository;
import com.capstone1.repository.LifeCoachRepository;
import com.capstone1.util.LifeCoachConstants;
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
public class LifeCoachServiceImpl implements LifeCoachService{

	@Autowired
	private LifeCoachRepository lifeCoachRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Environment environment;

	@Override
	public LifeCoachDTO signUp(LifeCoachDTO lifeCoachDTO) {
		lifeCoachRepository.saveAndFlush(modelMapper.map(lifeCoachDTO, LifeCoach.class));
		return lifeCoachDTO;
	}

	@Override
	public String login(String username, String password) throws LifeCoachNotFoundException {
		LifeCoach coach = lifeCoachRepository.findByName(username);
		if(coach != null) {
			if(coach.getPassword().equals(password))
				return environment.getProperty(LifeCoachConstants.LIFECOACH_LOGIN_SUCCESS.toString());
			else
				return environment.getProperty(LifeCoachConstants.LIFECOACH_LOGIN_FAILED.toString());
		} else {
			throw new LifeCoachNotFoundException(environment.getProperty(LifeCoachConstants.LIFECOACH_NOT_FOUND.toString()));
		}
	}

	@Override
	public LifeCoachDTO getLifeCoachProfile(int lifeCoachId) throws LifeCoachNotFoundException {
		Optional<LifeCoach> lifeCoachOp = lifeCoachRepository.findById(lifeCoachId);
		if(lifeCoachOp.isPresent()) {
			return modelMapper.map(lifeCoachOp.get(), LifeCoachDTO.class);
		} else {
			throw new LifeCoachNotFoundException(environment.getProperty(LifeCoachConstants.LIFECOACH_NOT_FOUND.toString()));
		}
	}

	@Override
	public List<AppointmentDTO> getUpcomingSchedule(int lifeCoachId) throws LifeCoachNotFoundException {
		Optional<LifeCoach> coachOp = lifeCoachRepository.findById(lifeCoachId);
		LifeCoach coach = null;
		List<AppointmentDTO> appointmentDTOs = new ArrayList<>();
		if(coachOp.isPresent()) {
			coach = coachOp.get();
			List<Appointment> appointments = appointmentRepository.findAllUpcomingAppointmentsForLifeCoach(lifeCoachId);
			for(Appointment app : appointments) {
				appointmentDTOs.add(modelMapper.map(app,AppointmentDTO.class));
			}
			return appointmentDTOs;
		} else {
			throw new LifeCoachNotFoundException(environment.getProperty(LifeCoachConstants.LIFECOACH_NOT_FOUND.toString()));
		}
	}
}
