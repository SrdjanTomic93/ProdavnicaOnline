package com.projekti.service;

import java.util.List;
import java.util.Optional;

import com.projekti.entities.Bill;
import com.projekti.entities.dto.BillDTO;

public interface BillDao {

	public Bill addNewBill(BillDTO bill);

	Bill addOfferToBill(Integer id, Integer offer);

	public List<Bill> findByDate(String startDate, String endDate);

	public Bill addUserToBill(Integer id, Integer user);

	void deleteBillById(Integer id);

	public Optional<Bill> findBillById(Integer id);
}
