package com.oufk.foodguru.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oufk.foodguru.models.FastFoodChain;

/*
 * Repository used to access FastFoodChain data from MySQL database
 */

@Repository
public interface FastFoodChainRepository extends JpaRepository<FastFoodChain, Long> {
	Optional<FastFoodChain> findByName(String name);
	
	Optional<FastFoodChain> findByAddress(String address);
	
    Boolean existsByName(String name);

    Boolean existsByAddress(String address);
}
