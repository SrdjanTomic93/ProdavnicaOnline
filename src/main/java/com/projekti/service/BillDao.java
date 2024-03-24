package com.projekti.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.projekti.entities.BillEntity;

public interface BillDao {
      
	
	  BillEntity addOfferToBill(Integer id, Integer offer);
	  
	  public List<BillEntity> findByDate(
	             String startDate,
	             String endDate);
	    
}
