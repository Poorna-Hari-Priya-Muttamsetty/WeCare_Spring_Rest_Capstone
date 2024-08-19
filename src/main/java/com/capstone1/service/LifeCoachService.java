package com.capstone1.service;

import com.capstone1.dto.AppointmentDTO;
import com.capstone1.dto.LifeCoachDTO;
import com.capstone1.exception.LifeCoachNotFoundException;
import java.util.List;

public interface LifeCoachService {

	LifeCoachDTO signUp(LifeCoachDTO lifeCoachDTO);
	
	String login(String username, String password) throws LifeCoachNotFoundException;
	
	LifeCoachDTO getLifeCoachProfile(int lifeCoachId) throws LifeCoachNotFoundException;
	
	List<AppointmentDTO> getUpcomingSchedule(int lifeCoachId) throws LifeCoachNotFoundException;
}
