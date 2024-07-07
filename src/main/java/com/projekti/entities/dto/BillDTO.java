package com.projekti.entities.dto;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

public class BillDTO {

	@AssertFalse
	private boolean paymentMode;
	@AssertFalse
	private boolean paymentCanceled;

	@NotBlank
	@Pattern(regexp = "\\d{4}/\\d{2}/\\d{2}", message = "Date must be in the following format: yyyy/MM/dd.")
	@Past
	private String billCreated;

}
