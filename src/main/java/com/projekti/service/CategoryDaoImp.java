package com.projekti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekti.entities.Category;
import com.projekti.entities.dto.CategoryDTO;
import com.projekti.repositories.CategoryRepository;

@Service
public class CategoryDaoImp implements CategoryDao {

	@Autowired
	CategoryRepository categoryRepository;

	public Category addNewCategory(CategoryDTO category) {
		Category cat = new Category();
		cat.setName(category.getName());
		cat.setCategoryDescription(category.getCategoryDescription());

		categoryRepository.save(cat);
		return cat;
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

}
