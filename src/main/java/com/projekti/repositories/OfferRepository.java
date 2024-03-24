package com.projekti.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projekti.entities.OfferEntity;
import com.projekti.entities.VoucherEntity;

public interface OfferRepository extends JpaRepository<OfferEntity, Integer> {


	

}
