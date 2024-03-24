package com.projekti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekti.entities.CategoryEntity;
import com.projekti.entities.dto.CategoryDTO;
import com.projekti.repositories.CategoryRepository;
@Service
public class CategoryDaoImp implements CategoryDao {

	@Autowired
	CategoryRepository categoryRepository;
	
	public CategoryEntity dodajKategoriju(CategoryDTO category)
	{
		CategoryEntity cat=new CategoryEntity();
		cat.setName(category.getName());
		cat.setCategoryDescription(category.getCategoryDescription());
		
		categoryRepository.save(cat);
		return cat;
	}
	
	
	
	
}
