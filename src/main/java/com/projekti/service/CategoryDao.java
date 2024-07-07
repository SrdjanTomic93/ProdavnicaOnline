package com.projekti.service;

import java.util.List;

import com.projekti.entities.Category;
import com.projekti.entities.dto.CategoryDTO;

public interface CategoryDao {

	public Category addNewCategory(CategoryDTO category);

	public List<Category> getAllCategories();
}
