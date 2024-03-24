package com.projekti.controllers;

import java.text.SimpleDateFormat;
import java.util.Collections;
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
import com.projekti.entities.OfferEntity;
import com.projekti.entities.UserEntity;
import com.projekti.entities.VoucherEntity;
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
	
	Date curentDate=new Date();
	SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");
	/*
	@RequestMapping(method=RequestMethod.POST)
	public VoucherEntity addVoucher(@RequestParam String expirationDate,@RequestParam boolean isUsed)
	{
		VoucherEntity voucher=new VoucherEntity();
		voucher.setExpirationDate(expirationDate);
		voucher.setUsed(isUsed);
		
		voucherRepository.save(voucher);
		return voucher;
	}
	@RequestMapping(method=RequestMethod.GET)
	public Iterable<VoucherEntity>getAllVoucher()
	{
		return voucherRepository.findAll();
	}
	*/
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?>dodajNoviVaucer(@Valid@RequestBody VoucherDTO voucher, BindingResult result)
	{
	if (result.hasErrors()) {
		return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
	VoucherEntity vau=voucherDao.dodajVaucer(voucher);
	return new ResponseEntity<>(voucher,HttpStatus.CREATED);
	
	}
	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
		}
	
	
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}/offer")//4.4
	public String addOfferToVoucher(@PathVariable Integer id,@RequestParam Integer offer )
	{
		return voucherDao.addOfferToVoucher(id, offer);
	}
	/*
	@RequestMapping(method=RequestMethod.PUT, value="/{id}/user")//4.5
	public VoucherEntity addUserToVoucher(@PathVariable Integer id,@RequestParam Integer user)
	{
		VoucherEntity voucher=voucherRepository.findById(id).get();
		UserEntity us=userRepository.findById(user).get();
		if(us.getUs().toString().equals("CUSTOMER"))
		{
		
		
		voucher.setUser(us);
		return voucherRepository.save(voucher);
		}
		else return null;
	}*/
	/*	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/user")//3.4.6
	public ResponseEntity<String> addUserToVoucher(@PathVariable Integer id, @RequestParam Integer user) {
	    VoucherEntity voucher = voucherRepository.findById(id).orElse(null);
	    UserEntity userEntity = userRepository.findById(user).orElse(null);

	    if (voucher != null && userEntity != null && userEntity.getUs().toString().equals("CUSTOMER")) {
	        voucher.setUser(userEntity);
	        voucherRepository.save(voucher);
	        return ResponseEntity.ok("Korisnik je uspešno dodat na vaučer.");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mora biti CUSTOMER");
	    }
	}*/
	 @PutMapping("/{id}/user")
	    public ResponseEntity<String> addUserToVoucher(@PathVariable Integer id, @RequestParam Integer user) {
	        // Provera korisničkih prava i ostalo...
	        String result = voucherDao.addUserToVoucher(id, user);
	        return ResponseEntity.ok(result);
	    }
	
	@PutMapping("/{id}/user/update") // Promenjena anotacija na @PutMapping
	public ResponseEntity<String> updateUserInVoucher(
	        @PathVariable Integer id,
	        @RequestParam Integer user,
	        @RequestBody VoucherEntity updatedVoucher) { // Dodat novi parametar @RequestBody za primanje podataka za ažuriranje

	    VoucherEntity voucher = voucherRepository.findById(id).orElse(null);
	    UserEntity userEntity = userRepository.findById(user).orElse(null);

	    if (voucher != null && userEntity != null && userEntity.getUs().toString().equals("CUSTOMER")) {
	        // Ažurirajte potrebne podatke na osnovu primljenog objekta updatedVoucher
	        // Na primer: voucher.setNaziv(updatedVoucher.getNaziv());

	        voucher.setUser(userEntity);
	        voucherRepository.save(voucher);
	        return ResponseEntity.ok("Korisnik je uspešno dodat na vaučer.");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mora biti CUSTOMER");
	    }
	}
	
	@DeleteMapping("/{id}/voucher") // Promenjena anotacija na @DeleteMapping
	public ResponseEntity<String> deleteVoucher(@PathVariable Integer id) {
	    VoucherEntity voucher = voucherRepository.findById(id).orElse(null);

	    if (voucher != null) {
	        voucherRepository.delete(voucher);
	        return ResponseEntity.ok("Vaučer je uspešno obrisan.");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaučer nije pronađen.");
	    }
	}
	
	
	

	
	


	@RequestMapping(method = RequestMethod.GET, value = "/project/vouchers/findByOffer/{offerId}")//4.8
	public List<VoucherEntity> findVouchersByOffer(@PathVariable Integer offerId) {
	    OfferEntity offer = offerRepository.findById(offerId).orElse(null);
	    if (offer == null) {
	        // Vraćanje prazne liste ili odgovarajuće poruke u zavisnosti od zahteva
	        return Collections.emptyList();
	    } else {
	        return voucherRepository.findByOffers(offer);
	    }
	}

	@RequestMapping(method = RequestMethod.GET, value = "/project/vouchers/findNonExpiredVoucher")//4.9 lista svih vaucera, koji nisu istekli
	public List<VoucherEntity> findNonExpiredVouchers() {

		return voucherRepository.findByExpirationDateAfter(timeFormat.format(curentDate));
		
		
	}
 
	@RequestMapping(method = RequestMethod.GET, value="/admin")
	@JsonView(Views.Admin.class)	
	public List<VoucherEntity> getAllUsersAdmin() {
	    return voucherRepository.findAll();
	}
	

	@RequestMapping(method = RequestMethod.GET, value="/private")
	@JsonView(Views.Private.class)	
	public List<VoucherEntity> getAllUsersPrivate() {
	    return voucherRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value="/public")
	@JsonView(Views.Public.class)	
	public List<VoucherEntity> getAllUsersPublic() {
	    return voucherRepository.findAll();
	}
	
		
	}
	
	
