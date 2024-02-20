package com.microservice.ratingservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ratingservice.entities.Rating;
import com.microservice.ratingservice.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	private RatingService ratingservice;
	
	//create
	@PostMapping
	public ResponseEntity<Rating> create(@RequestBody Rating rating){
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingservice.createRating(rating));
	}
	
	//get all rating
	
	@GetMapping
	public ResponseEntity<List<Rating>> getAll(){
		
		
		return ResponseEntity.status(HttpStatus.OK).body(ratingservice.getAllRatings());
	}
	
	//get all by userid
	
	@GetMapping("/userid/{userid}")
	public ResponseEntity<List<Rating>> getAllByUserid(@PathVariable String userid){
		
		
		return ResponseEntity.status(HttpStatus.OK).body(ratingservice.getAllRatingByUserId(userid));
	}
	
	
	//get all by hotel id
	
	@GetMapping("/hotelid/{hotelid}")
	public ResponseEntity<List<Rating>> getAllByHotelId(@PathVariable String hotelid){
		
		
		return ResponseEntity.status(HttpStatus.OK).body(ratingservice.getAllRatingByHotelId(hotelid));
	}
	

}
