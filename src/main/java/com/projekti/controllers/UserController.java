package com.projekti.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.projekti.entities.UserEntity;
import com.projekti.entities.userRole;
import com.projekti.entities.dto.UserDto;
import com.projekti.repositories.UserRepository;
import com.projekti.security.Views;
import com.projekti.service.UserDao;
import com.projekti.service.UserDaoImp;
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserDao userDao;
	/*
	@RequestMapping(method=RequestMethod.POST)
	public UserEntity addUserEntity(@Valid @RequestParam String firstName, @RequestParam String lastName,@RequestParam String userName,
			@RequestParam String password,@RequestParam String email,@RequestParam userRole us, BindingResult result)
	{
		if (result.hasErrors()) {
			return new UserEntity(createErrorMessage(result), HttpStatus.BAD_REQUEST);
			} 
		else {
		UserEntity user=new UserEntity();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(email);
		user.setUs(us);
		
		userRepository.save(user);
		return user;
		}
	}
	
	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
		}
	*/
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewUser(@Valid @RequestBody UserDto newUser, BindingResult result) {
	if (result.hasErrors()) {
	return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
	}
	UserEntity user=userDao.dodajUser(newUser);
	return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
		}
	@RequestMapping(method = RequestMethod.GET, value="/public")
	@JsonView(Views.Public.class)	
	public List<UserEntity> getAllUsersPublic() {
	    return userRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/admin")
	@JsonView(Views.Admin.class)	
	public List<UserEntity> getAllUsersAdmin() {
	    return userRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/private")
	@JsonView(Views.Private.class)	
	public List<UserEntity> getAllUsersPrivate() {
	    return userRepository.findAll();
	}
	
}
