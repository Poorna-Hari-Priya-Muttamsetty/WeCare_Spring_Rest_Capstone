package com.capstone1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class LifeCoachDTO {

	@NotNull
    private int id;
	
	@NotNull(message = "{name.must}")
    private String name;
	
	@Email(message = "{email.valid}")
	@NotNull(message = "{email.must}")
    private String email;
	
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,12}$", message = "{password.pattern}")
    private String password;
    
    @NotNull(message ="{specialty.must}")
    private String specialty;
    
	public LifeCoachDTO(int id, String name, String email, String password, String specialty) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.specialty = specialty;
	}
	public LifeCoachDTO() {
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
    
    
}
