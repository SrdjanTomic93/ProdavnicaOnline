package com.projekti.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projekti.entities.OfferEntity;
import com.projekti.entities.VoucherEntity;

public interface VoucherRepository extends JpaRepository<VoucherEntity, Integer> {

	List<VoucherEntity> findByOffers(OfferEntity offer);

	List<VoucherEntity> findByExpirationDateAfter(String string);


	







	

}
