package com.projekti.entities.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class VoucherDTO {
	    @NotBlank
	    @Pattern(regexp = "\\d{4}/\\d{2}/\\d{2}", message = "Datum treba da bude u formatu yyyy/MM/dd")
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
