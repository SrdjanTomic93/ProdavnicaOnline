package com.projekti.service;

import com.projekti.entities.UserEntity;
import com.projekti.entities.dto.UserDto;

public interface UserDao {

	public UserEntity dodajUser(UserDto newUser);
}
