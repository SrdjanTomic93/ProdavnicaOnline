package com.projekti.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projekti.entities.Offer;
import com.projekti.entities.User;
import com.projekti.entities.Voucher;
import com.projekti.entities.dto.VoucherDTO;
import com.projekti.repositories.OfferRepository;
import com.projekti.repositories.UserRepository;
import com.projekti.repositories.VoucherRepository;

@Service
public class VoucherDaoImp implements VoucherDao {

	@Autowired
	VoucherRepository voucherRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OfferRepository offerRepository;

	public Voucher dodajVaucer(VoucherDTO voucher) {
		Voucher vou = new Voucher();
		vou.setExpirationDate(voucher.getExpirationDate());
		vou.setUsed(voucher.isUsed());

		voucherRepository.save(vou);
		return vou;

	}

	public String addOfferToVoucher(Integer id, Integer offer) {
		Voucher voucher = voucherRepository.findById(id).orElse(null);
		Offer of = offerRepository.findById(offer).orElse(null);

		if (voucher != null && of != null) {
			voucher.setOffers(of);
			voucherRepository.save(voucher);
			return "The offer has been added to the voucher";

		} else
			return "Error, please check the validity of the input again";
	}

	public String addUserToVoucher(Integer voucherId, Integer userId) {
		Voucher voucher = voucherRepository.findById(voucherId).orElse(null);
		User userEntity = userRepository.findById(userId).orElse(null);

		if (voucher != null && userEntity != null && userEntity.getUs().toString().equals("CUSTOMER")) {
			voucher.setUser(userEntity);
			voucherRepository.save(voucher);
			return "User is added successfuly.";
		} else {
			return "User must be CUSTOMER";
		}
	}

	public String updateUserInVoucher(Integer id, Integer user, Voucher updatedVoucher) {
		Voucher voucher = voucherRepository.findById(id).orElse(null);
		User userEntity = userRepository.findById(user).orElse(null);

		if (voucher != null && userEntity != null && userEntity.getUs().toString().equals("CUSTOMER")) {
			voucher.setUser(userEntity);
			voucherRepository.save(voucher);
			return ("User is added successfuly.");
		} else {
			return "Must be CUSTOMER";
		}
	}

	public ResponseEntity<String> deleteVoucher(Integer id) {
		Voucher voucher = voucherRepository.findById(id).orElse(null);

		if (voucher != null) {
			voucherRepository.delete(voucher);
			return ResponseEntity.ok("Vaučer je uspešno obrisan.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Voucher not founded.");
		}
	}

	public List<Voucher> findVouchersByOffer(Integer offerId) {
		Offer offer = offerRepository.findById(offerId).orElse(null);
		if (offer == null) {
			return Collections.emptyList();
		} else {
			return voucherRepository.findByOffers(offer);
		}
	}

	public List<Voucher> getAllVouchers() {
		return voucherRepository.findAll();
	}

}
