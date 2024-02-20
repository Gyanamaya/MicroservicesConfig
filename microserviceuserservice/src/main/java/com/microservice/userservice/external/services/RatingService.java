package com.microservice.userservice.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.microservice.userservice.entities.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {
	
	
	//get
	@GetMapping("/ratings/{ratingid}")
	public Rating getRating(@PathVariable String ratingid);
	
	//post
	@PostMapping("/ratings")
	public Rating createRating(Rating rating);
	
	//put
	
	public Rating updateRating();

}
