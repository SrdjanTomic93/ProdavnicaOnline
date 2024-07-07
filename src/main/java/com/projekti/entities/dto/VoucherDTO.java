package com.projekti.entities.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class VoucherDTO {

	@NotBlank
	@Pattern(regexp = "\\d{4}/\\d{2}/\\d{2}", message = "Date must be in the following format: yyyy/MM/dd.")
	private String expirationDate;

	@NotNull
	private boolean isUsed;

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public VoucherDTO() {

	}

}
