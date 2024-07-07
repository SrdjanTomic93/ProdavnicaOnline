package com.projekti.service;

import com.projekti.entities.Offer;
import com.projekti.entities.dto.OfferDTO;

public interface OfferDao {

	public Offer dodajPonudu(OfferDTO offerDTO);

	public String addUserToOffer(Integer id, Integer users);

	public String addCategoryToOffer(Integer id, Integer category);
}
