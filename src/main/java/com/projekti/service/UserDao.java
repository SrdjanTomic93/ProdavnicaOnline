package com.projekti.service;

import com.projekti.entities.User;
import com.projekti.entities.dto.UserDto;

public interface UserDao {

	public User addUser(UserDto newUser);
}
