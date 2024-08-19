package com.capstone1.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="life_coaches")
public class LifeCoach {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String specialty;
    @OneToMany(mappedBy = "lifeCoach", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
    
    

	public LifeCoach(int id, String name, String email, String password, String specialty,
			List<Appointment> appointments) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.specialty = specialty;
		this.appointments = appointments;
	}

	public LifeCoach() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public String toString() {
		return "LifeCoach [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", specialty="
				+ specialty + "]";
	}
    
    
}
