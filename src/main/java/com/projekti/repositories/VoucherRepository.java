package com.projekti.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projekti.entities.Offer;
import com.projekti.entities.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {

	List<Voucher> findByOffers(Offer offer);

	List<Voucher> findByExpirationDateAfter(String string);

}
