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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.projekti.security.Views;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity {
	
	
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
	@NotNull(message = "First name must be provided.")
	@Size(min = 5, message = "Polje mora imati najmanje 5 karaktera")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).*$", message = "Polje mora sadržati slova i brojeve")
	@Column(nullable = false)
	private String password;
	
	@JsonView(Views.Private.class)
	@NotNull(message = "First name must be provided.")
	@Column(nullable = false)
	private String email;
	
	
	@JsonView(Views.Admin.class)
	@NotNull(message = "First name must be provided.")
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

	public UserEntity(Integer id, String firstName, String lastName, String userName, String password, String email,userRole us) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
	    this.us=us;
	}

	public UserEntity() {
	
	}
	@JsonView(Views.Private.class)
	@JsonIgnore//3.2.2 korisnik moze da kreira vise ponuda, dok jednu ponudu moze kreirati samo jedan korisnik
	@OneToMany(mappedBy="users",fetch=FetchType.LAZY,cascade= {CascadeType.REFRESH})
	private List<OfferEntity>offers=new ArrayList<>();


	public List<OfferEntity> getOffers() {
		return offers;
	}

	public void setOffers(List<OfferEntity> offers) {
		this.offers = offers;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = { 
	CascadeType.REFRESH })
	private List<BillEntity>bill=new ArrayList<>();//korisnik moze da ima jedan racun, a na racun podrazumeva samo jednog korisnika


	public List<BillEntity> getBill() {
		return bill;
	}

	public void setBill(List<BillEntity> bill) {
		this.bill = bill;
	}
	   @JsonView(Views.Private.class)
	   @JsonIgnore
	   @OneToMany(mappedBy="user",fetch=FetchType.LAZY,cascade= {CascadeType.REFRESH})
	   private List<VoucherEntity>voucher=new ArrayList<>();


	public List<VoucherEntity> getVoucher() {
		return voucher;
	}

	public void setVoucher(List<VoucherEntity> voucher) {
		this.voucher = voucher;
	}


	
	
	

	
	
}
