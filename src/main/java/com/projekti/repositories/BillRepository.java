package com.projekti.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projekti.entities.Bill;
import com.projekti.entities.dto.BillDTO;

public interface BillRepository extends JpaRepository<Bill, Integer> {

	Iterable<Bill> findAllByUser(Integer user);

	List<Bill> findByBillCreatedBetween(String startDate, String endDate);

	void save(BillDTO bill);

}
