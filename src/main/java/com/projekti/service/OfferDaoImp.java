package com.projekti.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.projekti.entities.Category;
import com.projekti.entities.Offer;
import com.projekti.entities.User;
import com.projekti.entities.dto.OfferDTO;
import com.projekti.repositories.CategoryRepository;
import com.projekti.repositories.OfferRepository;
import com.projekti.repositories.UserRepository;

@Service
public class OfferDaoImp implements OfferDao {

	@Autowired
	OfferRepository offerRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CategoryRepository categoryRepository;

	public Offer dodajPonudu(OfferDTO offerDTO) {
		Offer offer = new Offer();
		offer.setOfferName(offerDTO.getOfferName());
		offer.setOfferDescription(offerDTO.getOfferDescription());
		offer.setOfferCreated(offerDTO.getOfferCreated());
		offer.setOfferExpires(offerDTO.getOfferExpires());
		offer.setRegularPrice(offerDTO.getRegularPrice());
		offer.setActionPrice(offerDTO.getActionPrice());
		offer.setOf(offerDTO.getOf());
		offer.setNumberOfOffers(offerDTO.getNumberOfOffers());

		offerRepository.save(offer);
		return offer;

	}

	Date currentDate = new Date();

	SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");// current date

	long timel = currentDate.getTime() + 10 * 24 * 60 * 60 * 1000;// Initialize the date to 10 days from the current
																	// date

	public String addUserToOffer(@PathVariable Integer id,
			@RequestParam Integer users)/*
										 * Adding category and user who created the offer. The offer expires 10 days
										 * from the creation date and only the "SELLER" has the right to create it.
										 */
	{
		Offer offer = offerRepository.findById(id).get();
		User use = userRepository.findById(users).get();

		if (use.getUs().toString().equals("SELLER")) {
			offer.setOfferCreated(timeFormat.format(currentDate));
			offer.setOfferExpires(timeFormat.format(timel));

			offer.setUsers(use);
			offerRepository.save(offer);
			return "User is added";
		} else

			return "User must be SELLER";

	}

	public String addCategoryToOffer(@PathVariable Integer id, @RequestParam Integer category) {

		Offer offer = offerRepository.findById(id).orElse(null);
		Category cat = categoryRepository.findById(category).orElse(null);
		if (offer != null && cat != null) {
			offer.setCategory(cat);
			offerRepository.save(offer);
			return "Category is added successfully";

		} else
			return "Incorrect category or offer entered";
	}

}
