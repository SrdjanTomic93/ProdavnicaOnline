package com.projekti.entities.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryDTO {

	private Integer id;

	@NotNull(message = "First name must be provided.")
	private String name;

	@Size(min = 5, max = 50, message = "The field must have at least 5 and at most 50 characters.")
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
