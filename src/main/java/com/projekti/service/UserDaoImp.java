package com.projekti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekti.entities.User;
import com.projekti.entities.dto.UserDto;
import com.projekti.repositories.UserRepository;

@Service
public class UserDaoImp implements UserDao {

	@Autowired
	UserRepository userRepository;

	public User addUser(UserDto newUser) {
		User user = new User();
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
