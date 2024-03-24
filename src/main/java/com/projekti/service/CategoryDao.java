package com.projekti.service;

import com.projekti.entities.CategoryEntity;
import com.projekti.entities.dto.CategoryDTO;

public interface CategoryDao {

	public CategoryEntity dodajKategoriju(CategoryDTO category);
}
