package com.projekti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.projekti.entities.BillEntity;
import com.projekti.entities.OfferEntity;
import com.projekti.entities.dto.BillDTO;
import com.projekti.repositories.BillRepository;
import com.projekti.repositories.OfferRepository;

@Service
public class BillDaoImp implements BillDao {

	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private BillRepository billRepository;
	

	
	 @Override
	public BillEntity addOfferToBill( @PathVariable Integer id, Integer offer)//4.2.1 izmena broja kupljenih ponuda(kada dodamo racun na ponudu, broj ponuda se smanji)
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
	 // metoda za pronalazak racuna izmedju dva datuma
	 public List<BillEntity> findByDate(
	            @PathVariable("startDate") @JsonFormat(pattern="yyyy/MM/dd",shape=Shape.STRING)  String startDate,
	            @PathVariable("endDate") @JsonFormat(pattern="yyyy/MM/dd",shape=Shape.STRING) String endDate
	    ) {
	        return billRepository.findByBillCreatedBetween(startDate, endDate);
	    }
	 
}
