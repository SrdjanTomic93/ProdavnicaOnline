package com.projekti.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.projekti.entities.Bill;
import com.projekti.entities.dto.BillDTO;
import com.projekti.repositories.BillRepository;
import com.projekti.repositories.OfferRepository;
import com.projekti.repositories.UserRepository;
import com.projekti.security.Views;
import com.projekti.service.BillDao;

@RestController
@RequestMapping(path = "/api/v1/bill")
public class BillController {

	@Autowired
	private BillDao billDao;

	@Autowired
	BillRepository billRepository;

	@Autowired
	OfferRepository offerRepository;

	@Autowired
	UserRepository userRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewBill(@Valid @RequestBody BillDTO billDTO, BindingResult result) {
		if (result.hasErrors()) {

			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);

		}
		Bill bill = billDao.addNewBill(billDTO);
		return new ResponseEntity<BillDTO>(billDTO, HttpStatus.CREATED);
	}

	private Object createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}

	@RequestMapping(method = RequestMethod.GET)

	public Iterable<Bill> getAllBills() {
		return billRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/user") // 3.5
	public Bill addUserToBill(@PathVariable Integer id, @RequestParam Integer user) {
		return billDao.addUserToBill(id, user);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void delete(@PathVariable Integer id) {
		billDao.deleteBillById(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/f/{id}")
	@JsonView(Views.Admin.class)
	public Optional<Bill> findBillById(@PathVariable Integer id) {

		return billDao.findBillById(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/offer") // 4.2.2. Update the number of purchased offers
																		// (when
																		// a bill is added to an offer, the number of
																		// offers
																		// decreases)
	public Bill addOfferToBill(@PathVariable Integer id, @RequestParam Integer offer) {
		return billDao.addOfferToBill(id, offer);
	}

	@GetMapping("/findByDate/{startDate}/and/{endDate}") // 4.2.4
	public List<Bill> findByDate(@PathVariable String startDate, @PathVariable String endDate)

	{
		return billDao.findByDate(startDate, endDate);
	}

}
