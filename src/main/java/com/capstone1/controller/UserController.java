package com.capstone1.controller;

import com.capstone1.dto.AppointmentDTO;
import com.capstone1.dto.LifeCoachDTO;
import com.capstone1.dto.UserDTO;
import com.capstone1.exception.AppointmentNotFoundException;
import com.capstone1.exception.LifeCoachNotFoundException;
import com.capstone1.exception.UserNotFoundException;
import com.capstone1.service.UserService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/signUp")
	public ResponseEntity<UserDTO> signUp(@Valid @RequestBody UserDTO userDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(userDTO));
	}

	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestParam("user") String username, @RequestParam("pwd") String password) throws UserNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(userService.login(username, password));
	}

	@GetMapping("/userDetails/{userId}")
	public ResponseEntity<UserDTO> getUserProfile(@PathVariable("userId") int userId) throws UserNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUserProfile(userId));
	}

	@GetMapping("/allCoaches")
	public ResponseEntity<List<LifeCoachDTO>> getAllLifeCoaches() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getAllLifeCoaches());
	}

	@PostMapping("/saveAppointment")
	public ResponseEntity<AppointmentDTO> bookAppointment(@Valid @RequestBody AppointmentDTO appointmentDTO) throws UserNotFoundException, LifeCoachNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.bookAppointment(appointmentDTO));
	}

	@GetMapping("/allUpcomingAppointments/{id}")
	public ResponseEntity<List<AppointmentDTO>> getAllUpcomingAppointments(@PathVariable("id") int userId) throws UserNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUpcomingAppointments(userId));
	}

	@PutMapping("/rescheduleAppointment/{id}/{date}")
	public ResponseEntity<AppointmentDTO> rescheduleAppointment(@PathVariable("id") int appointmentId, @PathVariable("date") LocalDateTime date) throws AppointmentNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.rescheduleAppointment(appointmentId, date));
	}

	@DeleteMapping("/deleteAppointment/{id}")
	public ResponseEntity<AppointmentDTO> cancelAppointment(@PathVariable("id") int appointmentId) throws AppointmentNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(userService.cancelAppointment(appointmentId));
	}
}
