package com.projekti.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.projekti.security.Views;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VoucherEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	private Integer id;
	
	@JsonFormat(pattern="yyyy/MM/dd",shape=Shape.STRING)
	@Column
	@JsonView(Views.Public.class)
	 private String expirationDate;
	
	@Column
	@JsonView(Views.Admin.class)
	private boolean isUsed;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String exprationDate) {
		this.expirationDate = exprationDate;
	}
	public boolean isUsed() {
		return isUsed;
	}
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	public VoucherEntity(Integer id, String expirationDate, boolean isUsed) {
		
		this.id = id;
		this.expirationDate = expirationDate;
		this.isUsed = isUsed;
	}
	public VoucherEntity() {
		
	}
	@JsonView(Views.Private.class)
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="offers")
	private OfferEntity offers;
	
	
	public OfferEntity getOffers() {
		return offers;
	}
	public void setOffers(OfferEntity offers) {
		this.offers = offers;
	}
	@JsonView(Views.Private.class)
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="user")
	private UserEntity user;
	
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	
	
	
	
	
	
}
