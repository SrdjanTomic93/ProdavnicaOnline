package com.projekti.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projekti.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	

	

}
