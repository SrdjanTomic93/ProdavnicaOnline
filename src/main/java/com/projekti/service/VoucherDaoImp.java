package com.projekti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekti.entities.OfferEntity;
import com.projekti.entities.UserEntity;
import com.projekti.entities.VoucherEntity;
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
    
    
    public VoucherEntity dodajVaucer(VoucherDTO voucher)
    {
    	VoucherEntity vou=new VoucherEntity();
    	vou.setExpirationDate(voucher.getExpirationDate());
    	vou.setUsed(voucher.isUsed());
    	
    	voucherRepository.save(vou);
    	return vou;
    	
    }
    
    public String addOfferToVoucher( Integer id,Integer offer )
	{
		VoucherEntity voucher=voucherRepository.findById(id).orElse(null);
		OfferEntity of=offerRepository.findById(offer).orElse(null);
	   
		if(voucher !=null && of !=null)
		{
			voucher.setOffers(of);
			voucherRepository.save(voucher);
			return "Ponuda je dodata vauceru";
			
		}
		else 
			return "Greska, proverite ponovo ispravnost unosa";
	}
    

    public String addUserToVoucher(Integer voucherId, Integer userId) {
        VoucherEntity voucher = voucherRepository.findById(voucherId).orElse(null);
        UserEntity userEntity = userRepository.findById(userId).orElse(null);

        if (voucher != null && userEntity != null && userEntity.getUs().toString().equals("CUSTOMER")) {
            voucher.setUser(userEntity);
            voucherRepository.save(voucher);
            return "Korisnik je uspešno dodat na vaučer.";
        } else {
            return "Mora biti CUSTOMER";
        }
    }
    
    // Ostale metode DAO sloja
}
	

