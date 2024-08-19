package com.capstone1.controller;

import com.capstone1.dto.AppointmentDTO;
import com.capstone1.dto.LifeCoachDTO;
import com.capstone1.exception.LifeCoachNotFoundException;
import com.capstone1.service.LifeCoachService;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lifeCoach")
@Validated
public class LifeCoachController {

	@Autowired
	private LifeCoachService lifeCoachService;
	
	@PostMapping("/signup")
	public ResponseEntity<LifeCoachDTO> signUp(@Valid @RequestBody LifeCoachDTO lifeCoachDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(lifeCoachService.signUp(lifeCoachDTO));
	}

	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestParam("user") String username,@RequestParam("pwd") String password) throws LifeCoachNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(lifeCoachService.login(username, password));
	}

	@GetMapping("/coachDetails/{coachId}")
	public ResponseEntity<LifeCoachDTO> getLifeCoachProfile(@PathVariable("coachId") int lifeCoachId) throws LifeCoachNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(lifeCoachService.getLifeCoachProfile(lifeCoachId));
	}

	@GetMapping("/upcomingSchedules/{coachId}")
	public ResponseEntity<List<AppointmentDTO>> getUpcomingSchedule(@PathVariable("coachId") int lifeCoachId) throws LifeCoachNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(lifeCoachService.getUpcomingSchedule(lifeCoachId));
	}
	
}
