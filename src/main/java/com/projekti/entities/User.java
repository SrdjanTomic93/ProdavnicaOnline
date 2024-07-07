package com.projekti.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.projekti.security.Views;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "user_entity")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	@Column(nullable = false)
	private Integer id;

	@JsonView(Views.Private.class)
	@Column(nullable = false)
	private String firstName;

	@JsonView(Views.Private.class)
	@NotNull(message = "First name must be provided.")
	@Column(nullable = false)
	private String lastName;

	@JsonView(Views.Public.class)
	private String userName;

	@JsonView(Views.Private.class)
	@Column(nullable = false)
	private String password;

	@JsonView(Views.Private.class)
	@Column(nullable = false)
	private String email;

	@JsonView(Views.Admin.class)
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private userRole us;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public userRole getUs() {
		return us;
	}

	public void setUs(userRole us) {
		this.us = us;
	}

	public User(Integer id, String firstName, String lastName, String userName, String password, String email,
			userRole us) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.us = us;
	}

	public User() {

	}

	@JsonView(Views.Private.class)
	@JsonIgnore
	@OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<Offer> offers = new ArrayList<>();

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<Bill> bill = new ArrayList<>();

	public List<Bill> getBill() {
		return bill;
	}

	public void setBill(List<Bill> bill) {
		this.bill = bill;
	}

	@JsonView(Views.Private.class)
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<Voucher> voucher = new ArrayList<>();

	public List<Voucher> getVoucher() {
		return voucher;
	}

	public void setVoucher(List<Voucher> voucher) {
		this.voucher = voucher;
	}

}
