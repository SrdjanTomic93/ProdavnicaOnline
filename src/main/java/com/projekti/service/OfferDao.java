package com.projekti.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.projekti.entities.OfferEntity;
import com.projekti.entities.dto.OfferDTO;

public interface OfferDao {

	public OfferEntity dodajPonudu(OfferDTO offerDTO);
	
	public  String addUserToOffer(Integer id,  Integer users);
	
	public String addCategoryToOffer( Integer id, Integer category);
}
