package com.projekti.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.projekti.entities.CategoryEntity;
import com.projekti.entities.OfferEntity;
import com.projekti.entities.UserEntity;
import com.projekti.entities.dto.OfferDTO;
import com.projekti.repositories.CategoryRepository;
import com.projekti.repositories.OfferRepository;
import com.projekti.repositories.UserRepository;
import com.sun.mail.handlers.message_rfc822;

@Service
public class OfferDaoImp implements OfferDao {
    
	
	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public OfferEntity dodajPonudu(OfferDTO offerDTO)
	{
		OfferEntity offer=new OfferEntity();
		offer.setOfferName(offerDTO.getOfferName());
		offer.setOfferDescription(offerDTO.getOfferDescription());
		offer.setOfferCreated(offerDTO.getOfferCreated());
		offer.setOfferExpires(offerDTO.getOfferExpires());
		offer.setRegularPrice(offerDTO.getRegularPrice());
		offer.setActionPrice(offerDTO.getActionPrice());
		offer.setOf(offerDTO.getOf());
		offer.setBrojPonuda(offerDTO.getBrojPonuda());
		
		offerRepository.save(offer);
		return offer;
		
	}
	
	
Date currentDate=new Date();
	
	
	SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");// trenutni datum
	
	long timel=currentDate.getTime()+10*24*60*60*1000;// inicijalizacija datuma 10 dana od trenutnog
	
	
	
	public  String addUserToOffer(@PathVariable Integer id, @RequestParam Integer users)/*3.2.3 dodavanje kategorije i korisnika koji je kreirao ponudu. Ponuda 
	istice 10 dana  od trenutka kreiranje i pravo da je kreira ima samo "SELLER"*/
	{
			OfferEntity offer=offerRepository.findById(id).get();
			UserEntity use=userRepository.findById(users).get();
			
			if(use.getUs().toString().equals("SELLER"))
			{
			offer.setOfferCreated(timeFormat.format(currentDate));
			offer.setOfferExpires(timeFormat.format(timel));
			
		
			offer.setUsers(use);
              offerRepository.save(offer);
              return "User je uspesno dodat";
			}
			else
			
				return "User mora biti SELLER";
			
	}
	
	public String addCategoryToOffer(@PathVariable Integer id,@RequestParam Integer category)
	{
		
		OfferEntity offer=offerRepository.findById(id).orElse(null);
		CategoryEntity cat=categoryRepository.findById(category).orElse(null);
		 if (offer != null && cat != null) {
		        offer.setCategory(cat);
		        offerRepository.save(offer);
		        return "Kategorija je uspesno dodata ponudi";
		        
		 }
		        else
		      return "Pogresno uneta kategorija ili ponuda";
	}

	
	
	
}
