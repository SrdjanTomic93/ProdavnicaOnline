package com.projekti.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.projekti.security.Views;

@Entity
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	private Integer id;
	
	@Column(nullable = false)
	@JsonView(Views.Public.class)
	private String name;
	
	@Column(nullable = false)
	@JsonView(Views.Public.class)
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

	public CategoryEntity() {
	
	}

	public CategoryEntity(Integer id, String name, String categoryDescription) {
		
		this.id = id;
		this.name = name;
		this.categoryDescription = categoryDescription;
	}
	@JsonView(Views.Public.class)
	@JsonIgnore//3.2.1 jedna ponuda pripada tacno jednoj kategoriji, dok jedna kategorija moze imati vise ponuda
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = {
			CascadeType.REFRESH })
	private List<OfferEntity>offers=new ArrayList<>();
	
	
	
	
	
}
