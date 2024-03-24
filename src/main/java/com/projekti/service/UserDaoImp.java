package com.projekti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekti.entities.UserEntity;
import com.projekti.entities.dto.UserDto;
import com.projekti.repositories.UserRepository;

@Service
public class UserDaoImp implements UserDao{

	@Autowired
	UserRepository userRepository;
	
	public UserEntity dodajUser(UserDto newUser)
	{
		UserEntity user=new UserEntity();
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setUserName(newUser.getUserName());
		user.setPassword(newUser.getPassword());
		user.setEmail(newUser.getPassword());
		user.setUs(newUser.getUs());
		
		userRepository.save(user);
		return user;
		
		
		
		
	}
	
}
