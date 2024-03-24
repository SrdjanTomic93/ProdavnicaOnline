
package com.projekti.controllers;




import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.message.StructuredDataMessage.Format;
import org.apache.tomcat.jni.Time;
import org.hibernate.type.descriptor.java.LocalDateJavaDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters.LocalTimeToDateConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.projekti.entities.CategoryEntity;
import com.projekti.entities.OfferEntity;
import com.projekti.entities.UserEntity;
import com.projekti.entities.offerStatus;
import com.projekti.entities.userRole;
import com.projekti.entities.dto.OfferDTO;
import com.projekti.repositories.CategoryRepository;
import com.projekti.repositories.OfferRepository;
import com.projekti.repositories.UserRepository;
import com.projekti.service.OfferDao;


@RestController
@RequestMapping("/api/v1/offer")
public class OfferController {
/*	
	Date currentDate=new Date();
	
	
	SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd");// trenutni datum
	
	long timel=currentDate.getTime()+10*24*60*60*1000;// inicijalizacija datuma 10 dana od trenutnog
	
*/
	@Autowired
	OfferDao offerDao;
	@Autowired
	OfferRepository offerRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired 
	UserRepository userRepository;
	/*
	@RequestMapping(method=RequestMethod.POST)
	public OfferEntity addNewOffer(@RequestParam String offerName, @RequestParam String offerDescription,
		@RequestParam String offerCreated,@RequestParam String offerExpires,
		@RequestParam Integer regularPrice,@RequestParam Integer actionPrice,
		@RequestParam offerStatus of,@RequestParam Integer brojPonuda)
	{
		OfferEntity offer=new OfferEntity();
		offer.setOfferName(offerName);
		offer.setOfferDescription(offerDescription);
		offer.setOfferCreated(offerCreated);
		offer.setOfferExpires(offerExpires);
		offer.setRegularPrice(regularPrice);
		offer.setActionPrice(actionPrice);
		offer.setOf(of);
		offer.setBrojPonuda(brojPonuda);
 
		offerRepository.save(offer);
		return offer;
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<OfferEntity>getAllOffer()
	{
		return offerRepository.findAll();
	}
	*/
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?>dodajNovuPonudu(@Valid@RequestBody OfferDTO offerDTO, BindingResult result)
	{
		if(result.hasErrors()) 
		{
			
			return new ResponseEntity<>(createErrorMessage(result),HttpStatus.BAD_REQUEST);
			
		}
		OfferEntity offer=offerDao.dodajPonudu(offerDTO);
		return new ResponseEntity<OfferDTO>(offerDTO,HttpStatus.CREATED);
		
	}
	
	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
		}	
	@GetMapping()
	public List<OfferEntity>getAllEntities()
	{
		return offerRepository.findAll();
	}
	
	
	
	
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/category")
    public String addCategoryToOffer(@PathVariable Integer id,@RequestParam Integer category)
    {
		return offerDao.addCategoryToOffer(id, category);
    }
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/users")
	public  String addUserToOffer(@PathVariable Integer id, @RequestParam Integer users)
	{
			return offerDao.addUserToOffer( id,  users);
	}
	
	
	
	
	
	
	@PutMapping("/updateOffer")//3.2.4 update-ovana ponuda
    public OfferEntity update(@RequestBody OfferEntity offer)
    {
		offerRepository.save(offer);
		return offer;
    }
	
  
    
}