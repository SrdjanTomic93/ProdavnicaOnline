package com.projekti.service;

import com.projekti.entities.VoucherEntity;
import com.projekti.entities.dto.VoucherDTO;

public interface VoucherDao {

	public VoucherEntity dodajVaucer(VoucherDTO voucher);
	
	  public String addUserToVoucher(Integer voucherId, Integer userId);
	  
	  public String addOfferToVoucher( Integer id,Integer offer );
}
