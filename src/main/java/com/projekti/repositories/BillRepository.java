package com.projekti.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projekti.entities.BillEntity;
import com.projekti.entities.CategoryEntity;

public interface BillRepository extends JpaRepository<BillEntity,Integer> {

	Iterable<BillEntity> findAllByUser(Integer user);

	List<BillEntity> findByBillCreatedBetween(String startDate, String endDate);

	



	

	

	


	

	


	

	


	
	

	

}
