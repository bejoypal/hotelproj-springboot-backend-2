package com.hotel.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.backend.model.Cuisine;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Long> {

	
}
