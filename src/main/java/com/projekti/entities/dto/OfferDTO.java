package com.projekti.entities.dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.projekti.entities.offerStatus;

public class OfferDTO {

	
	
	 private Integer id;
	
	 @NotNull(message = "OfferName ne sme biti prazno")
	 private String offerName;
	 
	 @NotNull(message = "offerDescription ne sme biti prazno")
	 @Size(min=5,max=20,message="Polje mora imati izmedju 5 i 20 karaktera")
	 private String offerDescription;
	 
	 @NotNull(message = "OfferCreated ne sme biti prazno")
	 private String offerCreated;
	
	 @NotNull(message = "OfferExpires ne sme biti prazno.")
	 private String offerExpires;
	

	 @NotNull(message = "regularPrice ne sme biti prazno")
	 @Min(value = 1, message = "Najmanja vrednost mora biti jedan")
	 private Integer regularPrice;
	
	 @NotNull(message = "actionPrice ne sme biti prazno")
	 @Min(value = 1, message = "Najmanja vrednost mora biti jedan")
	 private Integer actionPrice;
	 
	 @NotNull(message = "of ne sme biti prazno")
	@Enumerated(EnumType.ORDINAL)
     private offerStatus of;
	
	@NotNull(message = "BrojPonuda ne sme biti prazno")
	private Integer brojPonuda;

	OfferDTO(){}

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

	public Integer getBrojPonuda() {
		return brojPonuda;
	}

	public void setBrojPonuda(Integer brojPonuda) {
		this.brojPonuda = brojPonuda;
	}
	
	
}
