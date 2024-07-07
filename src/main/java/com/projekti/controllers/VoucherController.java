package com.projekti.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.projekti.entities.Voucher;
import com.projekti.entities.dto.VoucherDTO;
import com.projekti.repositories.OfferRepository;
import com.projekti.repositories.UserRepository;
import com.projekti.repositories.VoucherRepository;
import com.projekti.security.Views;
import com.projekti.service.VoucherDao;

@RestController
@RequestMapping("/api/v1/voucher")
public class VoucherController {

	@Autowired
	private VoucherRepository voucherRepository;

	@Autowired
	private OfferRepository offerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	VoucherDao voucherDao;

	Date curentDate = new Date();
	SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewVoucher(@Valid @RequestBody VoucherDTO voucher, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		Voucher vau = voucherDao.dodajVaucer(voucher);
		return new ResponseEntity<>(voucher, HttpStatus.CREATED);

	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/offer") // 4.4
	public String addOfferToVoucher(@PathVariable Integer id, @RequestParam Integer offer) {
		return voucherDao.addOfferToVoucher(id, offer);
	}

	@PutMapping("/{id}/user")
	public ResponseEntity<String> addUserToVoucher(@PathVariable Integer id, @RequestParam Integer user) {

		String result = voucherDao.addUserToVoucher(id, user);
		return ResponseEntity.ok(result);
	}

	@PutMapping("/{id}/user/update")
	public ResponseEntity<String> updateUserInVoucher(@PathVariable Integer id, @RequestParam Integer user,
			@RequestBody Voucher updatedVoucher) {

		String result = voucherDao.updateUserInVoucher(id, user, updatedVoucher);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{id}/voucher")
	public ResponseEntity<String> deleteVoucher(@PathVariable Integer id) {
		return voucherDao.deleteVoucher(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/project/vouchers/findByOffer/{offerId}") // 4.8
	public List<Voucher> findVouchersByOffer(@PathVariable Integer offerId) {
		return voucherDao.findVouchersByOffer(offerId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/project/vouchers/findNonExpiredVoucher")
	public List<Voucher> findNonExpiredVouchers() {
		return voucherRepository.findByExpirationDateAfter(timeFormat.format(curentDate));

	}

	@RequestMapping(method = RequestMethod.GET, value = "/admin")
	@JsonView(Views.Admin.class)
	public List<Voucher> getAllVouchersAdmin() {
		return voucherDao.getAllVouchers();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/public")
	@JsonView(Views.Public.class)
	public List<Voucher> getAllVouchersPublic() {
		return voucherDao.getAllVouchers();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/private")
	@JsonView(Views.Private.class)
	public List<Voucher> getAllVouchersPrivate() {
		return voucherDao.getAllVouchers();
	}
}
