package com.projekti.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projekti.entities.Category;
import com.projekti.entities.dto.CategoryDTO;
import com.projekti.repositories.CategoryRepository;
import com.projekti.service.CategoryDao;

@RestController
@RequestMapping(path = "/api/v1/category")
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CategoryDao categoryDao;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		Category cat = categoryDao.addNewCategory(categoryDTO);
		return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);

	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}

	@GetMapping()
	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}

}
