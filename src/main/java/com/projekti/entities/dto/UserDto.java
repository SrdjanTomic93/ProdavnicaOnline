package com.projekti.entities.dto;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.projekti.entities.userRole;

public class UserDto {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull(message = "First name must be provided.")
	private String firstName;
	
	@NotNull(message = "First name must be provided.")
	private String lastName;
	
	@NotNull(message = "First name must be provided.")
	@Size(min=5,max=20, message="user name must be between {min} and {max} characters long")
	private String userName;
	
	@NotNull(message = "First name must be provided.")
	@Size(min = 5, message = "Polje mora imati najmanje 5 karaktera")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).*$", message = "Polje mora sadržati slova i brojeve")
	private String password;
	
	@NotNull(message = "First name must be provided.")
	private String email;
	
	@NotNull(message = "First name must be provided.")
	@Enumerated(EnumType.ORDINAL)
	private userRole us;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public userRole getUs() {
		return us;
	}

	public void setUs(userRole us) {
		this.us = us;
	}
	public UserDto() {
		
	}
	
}
