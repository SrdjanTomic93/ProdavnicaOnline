package com.projekti.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.projekti.entities.Bill;
import com.projekti.entities.Offer;
import com.projekti.entities.User;
import com.projekti.entities.dto.BillDTO;
import com.projekti.repositories.BillRepository;
import com.projekti.repositories.OfferRepository;
import com.projekti.repositories.UserRepository;

@Service
public class BillDaoImp implements BillDao {

	@Autowired
	private OfferRepository offerRepository;

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private UserRepository userRepository;

	public Bill addNewBill(BillDTO bill) {
		Bill newBill = new Bill();
		newBill.setPaymentMode(false);
		newBill.setPaymentCanceled(false);

		billRepository.save(bill);
		return newBill;

	}

	@Override
	public Bill addOfferToBill(Integer id, Integer offer)// when adding a bill to an offer, the number of offers
															// decreases
	{
		Bill bill = billRepository.findById(id).get();
		Offer off = offerRepository.findById(offer).get();
		if (off.getNumberOfOffers() > 0) {
			off.setNumberOfOffers(off.getNumberOfOffers() - 1);
			bill.setOffer(off);

			return billRepository.save(bill);
		} else
			return null;
	}

	// Method for finding bills between two dates
	public List<Bill> findByDate(
			@PathVariable("startDate") @JsonFormat(pattern = "yyyy/MM/dd", shape = Shape.STRING) String startDate,
			@PathVariable("endDate") @JsonFormat(pattern = "yyyy/MM/dd", shape = Shape.STRING) String endDate) {
		return billRepository.findByBillCreatedBetween(startDate, endDate);
	}

	public Bill addUserToBill(Integer id, Integer user) {
		Bill bill = billRepository.findById(id).get();
		User us = userRepository.findById(user).get();
		bill.setUser(us);

		return billRepository.save(bill);
	}

	@Override
	@Transactional
	public void deleteBillById(Integer id) {
		billRepository.deleteById(id);
	}

	public Optional<Bill> findBillById(Integer id) {

		return billRepository.findById(id);
	}
}
