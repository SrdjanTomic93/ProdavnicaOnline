
package com.projekti.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projekti.entities.Offer;
import com.projekti.entities.dto.OfferDTO;
import com.projekti.repositories.CategoryRepository;
import com.projekti.repositories.OfferRepository;
import com.projekti.repositories.UserRepository;
import com.projekti.service.OfferDao;

@RestController
@RequestMapping("/api/v1/offer")
public class OfferController {
	/*
	 * Date currentDate=new Date(); SimpleDateFormat timeFormat=new
	 * SimpleDateFormat("yyyy-MM-dd");// current time long
	 * timel=currentDate.getTime()+10*24*60*60*1000;// 10 days after current time
	 */
	@Autowired
	OfferDao offerDao;
	@Autowired
	OfferRepository offerRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	UserRepository userRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> dodajNovuPonudu(@Valid @RequestBody OfferDTO offerDTO, BindingResult result) {
		if (result.hasErrors()) {

			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);

		}
		Offer offer = offerDao.dodajPonudu(offerDTO);
		return new ResponseEntity<OfferDTO>(offerDTO, HttpStatus.CREATED);

	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}

	@GetMapping()
	public List<Offer> getAllEntities() {
		return offerRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/category")
	public String addCategoryToOffer(@PathVariable Integer id, @RequestParam Integer category) {
		return offerDao.addCategoryToOffer(id, category);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/users")
	public String addUserToOffer(@PathVariable Integer id, @RequestParam Integer users) {
		return offerDao.addUserToOffer(id, users);
	}

	@PutMapping("/updateOffer")
	public Offer update(@RequestBody Offer offer) {
		offerRepository.save(offer);
		return offer;
	}

}