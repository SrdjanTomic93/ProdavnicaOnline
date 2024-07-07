package com.projekti.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.projekti.entities.Voucher;
import com.projekti.entities.dto.VoucherDTO;

public interface VoucherDao {

	public Voucher dodajVaucer(VoucherDTO voucher);

	public String addUserToVoucher(Integer voucherId, Integer userId);

	public String addOfferToVoucher(Integer id, Integer offer);

	public String updateUserInVoucher(Integer id, Integer user, Voucher updatedVoucher);

	public ResponseEntity<String> deleteVoucher(Integer id);

	public List<Voucher> findVouchersByOffer(Integer offerId);

	public List<Voucher> getAllVouchers();
}
