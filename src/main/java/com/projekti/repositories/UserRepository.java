package com.projekti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projekti.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
