package com.projekti.entities.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryDTO {

	private Integer id;
	
	@NotNull(message = "First name must be provided.")
	private String name;
	
	@Size(min = 5, max=50, message = "Polje mora imati najmanje 5 karaktera, a najvise 50")
	private String categoryDescription;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public CategoryDTO() {
	
	}
	
	
	
	
}
