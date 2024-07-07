package com.projekti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projekti.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
