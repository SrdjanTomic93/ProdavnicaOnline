package com.projekti.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.projekti.entities.BillEntity;

import com.projekti.entities.OfferEntity;
import com.projekti.entities.UserEntity;
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
	
	@RequestMapping(method=RequestMethod.POST)
	public BillEntity addNewBill(@RequestParam boolean paymentMode, @RequestParam boolean paymentCanceled, @RequestParam String billCreated)
	{
		BillEntity bill=new BillEntity();
		bill.setPaymentMode(paymentMode);
		bill.setPaymentCanceled(paymentCanceled);
		bill.setBillCreated(billCreated);
		
		billRepository.save(bill);
		return bill;
			
	}
	@RequestMapping(method = RequestMethod.GET)
	@JsonView(Views.Admin.class)//3.3.4 
	public Iterable<BillEntity>getAllBills()
	{
		return billRepository.findAll();
	}
	/*
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/offer")///5.1 Kada se na jednom racunu doda ponuda, automatski se smanjuje broj ponuda
	public BillEntity addOfferToBill(@PathVariable Integer id,@RequestParam Integer offer)
	{
		BillEntity bill=billRepository.findById(id).get();
		OfferEntity off=offerRepository.findById(offer).get();
		if(off.getBrojPonuda()>0)
		{
			off.setBrojPonuda(off.getBrojPonuda()-1);
		    bill.setOffer(off);
		
		return billRepository.save(bill);
		}
		else return null;
	}
	*/
	
	
	
	
	
	
	@RequestMapping(method=RequestMethod.PUT,value="/{id}/user")//3.5
	public	BillEntity addUserToBill(@PathVariable Integer id, @RequestParam Integer user)
		{
			BillEntity bill=billRepository.findById(id).get();
			UserEntity us=userRepository.findById(user).get();
			bill.setUser(us);
			
			return billRepository.save(bill);	
		}
	
	@Transactional//3.3.6 brisanje svih racuna
	@RequestMapping(method=RequestMethod.DELETE,value="/{id}")
	public void  delete(@PathVariable Integer id)
	{
		billRepository.deleteById(id);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/f/{id}")//3.3.7//pronalazak svih racuna
	@JsonView(Views.Admin.class)
	public Optional<BillEntity> findBillById(@PathVariable Integer id)
	{
		
		return billRepository.findById(id);
	}
	/*
	 @GetMapping("/findByDate/{startDate}/and/{endDate}")//3.3.9 pronalazak svih racuna u odredjenom vremenskom periodu
	    public List<BillEntity> findByDate(
	            @PathVariable("startDate") @JsonFormat(pattern="yyyy/MM/dd",shape=Shape.STRING)  String startDate,
	            @PathVariable("endDate") @JsonFormat(pattern="yyyy/MM/dd",shape=Shape.STRING) String endDate
	    ) {
	        return billRepository.findByBillCreatedBetween(startDate, endDate);
	    }

	 */
	 @RequestMapping(method = RequestMethod.PUT, value = "/{id}/offer")//4.2.2.izmena broja kupljenih ponuda(kada dodamo racun na ponudu, broj ponuda se smanji)
	 public BillEntity addOfferToBill(@PathVariable Integer id, @RequestParam Integer offer)
	 {
		 return billDao.addOfferToBill(id, offer);
	 }
	 
	 @GetMapping("/findByDate/{startDate}/and/{endDate}")//4.2.4
	 public List<BillEntity> findByDate(@PathVariable String startDate,@PathVariable String endDate)
    
	 {
		 return billDao.findByDate(startDate, endDate);
	 }
	 
}


