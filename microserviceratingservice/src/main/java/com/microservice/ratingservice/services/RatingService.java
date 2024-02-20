package com.microservice.ratingservice.services;

import java.util.List;

import com.microservice.ratingservice.entities.Rating;

public interface RatingService {
	
	
	//create
	
	Rating createRating(Rating rating);
	
	//get all ratings
	
	List<Rating> getAllRatings();
	
	
	//get all by userid
	
	List<Rating> getAllRatingByUserId(String userid);
	
	//get all by hotel
	
	List<Rating> getAllRatingByHotelId(String hotelid);

}
