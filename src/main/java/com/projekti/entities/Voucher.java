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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.projekti.security.Views;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "voucher_entity")
public class Voucher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	private Integer id;

	@JsonFormat(pattern = "yyyy/MM/dd", shape = Shape.STRING)
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

	public Voucher(Integer id, String expirationDate, boolean isUsed) {

		this.id = id;
		this.expirationDate = expirationDate;
		this.isUsed = isUsed;
	}

	public Voucher() {

	}

	@JsonView(Views.Private.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "offers")
	private Offer offers;

	public Offer getOffers() {
		return offers;
	}

	public void setOffers(Offer offers) {
		this.offers = offers;
	}

	@JsonView(Views.Private.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
