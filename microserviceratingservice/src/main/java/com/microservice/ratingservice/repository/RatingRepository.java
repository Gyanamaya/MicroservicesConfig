package com.microservice.ratingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.ratingservice.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, String>{
	
	
	
	//Cuustom finder methods
	
	
	List<Rating> findByUserId(String userid);
	List<Rating> findByHotelId(String hotelid);

}
