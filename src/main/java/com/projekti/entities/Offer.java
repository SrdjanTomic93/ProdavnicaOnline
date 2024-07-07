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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.projekti.security.Views;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "offer_entity")
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	private Integer id;

	@Column(nullable = false)
	@JsonView(Views.Public.class)
	private String offerName;

	@Column(nullable = false)
	@JsonView(Views.Public.class)
	private String offerDescription;

	@JsonView(Views.Public.class)
	@JsonFormat(pattern = "yyyy/MM/dd", shape = Shape.STRING)
	@Column
	private String offerCreated;

	@JsonView(Views.Public.class)
	@JsonFormat(pattern = "yyyy/MM/dd", shape = Shape.STRING)
	@Column
	private String offerExpires;

	@JsonView(Views.Public.class)
	@Column(nullable = false)
	private Integer regularPrice;

	@JsonView(Views.Public.class)
	@Column(nullable = false)
	private Integer actionPrice;

	@JsonView(Views.Public.class)
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private offerStatus of;

	@JsonView(Views.Public.class)
	@Column(nullable = false)
	private Integer numberOfOffers;

	public Offer(Integer id, String offerName, String offerDescription, String offerCreated, String offerExpires,
			Integer regularPrice, Integer actionPrice, offerStatus of, Integer numberOfOffers) {

		this.id = id;
		this.offerName = offerName;
		this.offerDescription = offerDescription;
		this.offerCreated = offerCreated;
		this.offerExpires = offerExpires;
		this.regularPrice = regularPrice;
		this.actionPrice = actionPrice;
		this.of = of;
		this.numberOfOffers = numberOfOffers;

	}

	public Offer() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getOfferDescription() {
		return offerDescription;
	}

	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}

	public String getOfferCreated() {
		return offerCreated;
	}

	public void setOfferCreated(String offerCreated) {
		this.offerCreated = offerCreated;
	}

	public String getOfferExpires() {
		return offerExpires;
	}

	public void setOfferExpires(String offerExpires) {
		this.offerExpires = offerExpires;
	}

	public Integer getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(Integer regularPrice) {
		this.regularPrice = regularPrice;
	}

	public Integer getActionPrice() {
		return actionPrice;
	}

	public void setActionPrice(Integer actionPrice) {
		this.actionPrice = actionPrice;
	}

	public offerStatus getOf() {
		return of;
	}

	public void setOf(offerStatus of) {
		this.of = of;
	}

	public Integer getNumberOfOffers() {
		return numberOfOffers;
	}

	public void setNumberOfOffers(Integer brojPonuda) {
		this.numberOfOffers = brojPonuda;
	}

	@JsonView(Views.Public.class)
	@ManyToOne
	@JoinColumn(name = "category")
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "users")
	@JsonView(Views.Public.class)
	private User users;

	@JsonView(Views.Public.class)
	@JsonIgnore
	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<Bill> bill = new ArrayList<>();

	public List<Bill> getBill() {
		return bill;
	}

	public void setBill(List<Bill> bill) {
		this.bill = bill;
	}

	@JsonView(Views.Public.class)
	@JsonIgnore
	@OneToMany(mappedBy = "offers", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<Voucher> voucher = new ArrayList<>();

	public List<Voucher> getVoucher() {
		return voucher;
	}

	public void setVoucher(List<Voucher> voucher) {
		this.voucher = voucher;
	}

}
