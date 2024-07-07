package com.projekti.entities.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.projekti.entities.offerStatus;

public class OfferDTO {

	private Integer id;

	@NotNull(message = " OfferName must be provided.")
	private String offerName;

	@NotNull(message = "offerDescription must be provided.")
	@Size(min = 5, max = 20, message = "Field must have at least 5 ahd the most 20 characters.")
	private String offerDescription;

	@NotNull(message = "OfferCreated must be provided.")
	private String offerCreated;

	@NotNull(message = "OfferExpires must be provided.")
	private String offerExpires;

	@NotNull(message = "regularPrice must be provided.")
	@Min(value = 1, message = "The minimum value must be number bigger then zero.")
	private Integer regularPrice;

	@NotNull(message = "actionPrice must be provided.")
	@Min(value = 1, message = "The minimum value must be a number bigger than zero.")
	private Integer actionPrice;

	@NotNull(message = "of must be provided.")
	@Enumerated(EnumType.ORDINAL)
	private offerStatus of;

	@NotNull(message = "NumberOfOffers must be provided.")
	private Integer numberOfOffers;

	OfferDTO() {
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

	public void setBrojPonuda(Integer numberOfOffers) {
		this.numberOfOffers = numberOfOffers;
	}

}
