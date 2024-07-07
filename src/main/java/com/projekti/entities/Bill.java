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
@Table(name = "bill_entity")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	private Integer id;

	@Column(nullable = false)
	@JsonView(Views.Admin.class)
	private boolean paymentMode;

	@Column(nullable = false)
	@JsonView(Views.Admin.class)
	private boolean paymentCanceled;

	@JsonFormat(pattern = "yyyy/MM/dd", shape = Shape.STRING)
	@Column
	@JsonView(Views.Public.class)
	private String billCreated;

	public Bill(Integer id, boolean paymentMode, boolean paymentCanceled, String billCreated) {

		this.id = id;
		this.paymentMode = paymentMode;
		this.paymentCanceled = paymentCanceled;
		this.billCreated = billCreated;

	}

	public Bill() {

		this.paymentMode = false;
		this.paymentCanceled = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(boolean paymentMode) {
		this.paymentMode = paymentMode;
	}

	public boolean isPaymentCanceled() {
		return paymentCanceled;
	}

	public void setPaymentCanceled(boolean paymentCanceled) {
		this.paymentCanceled = paymentCanceled;
	}

	public String getBillCreated() {
		return billCreated;
	}

	public void setBillCreated(String billCreated) {
		this.billCreated = billCreated;
	}

	@JsonView(Views.Private.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "offer")
	private Offer offer;

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
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
