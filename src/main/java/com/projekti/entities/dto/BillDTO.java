package com.projekti.entities.dto;

import javax.persistence.Column;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class BillDTO {

	
	@AssertFalse
	private boolean paymentMode;
	@AssertFalse
	private boolean  paymentCanceled;
	
	 @NotBlank
	 @Pattern(regexp = "\\d{4}/\\d{2}/\\d{2}", message = "Datum treba da bude u formatu yyyy/MM/dd")
	 @Past
	 private String billCreated;
	
}
