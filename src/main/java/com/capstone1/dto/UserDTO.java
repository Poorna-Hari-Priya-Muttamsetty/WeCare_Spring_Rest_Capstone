package com.capstone1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserDTO {

	@NotNull
	private int id;
	
	@Email(message = "{email.valid}")
	@NotNull(message = "{email.must}")
    private String email;
	
	@NotNull(message = "{name.must}")
    private String username;
	
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,12}$", message = "{password.pattern}")
    private String password;
    
	public UserDTO(int id, String email, String username, String password) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public UserDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
