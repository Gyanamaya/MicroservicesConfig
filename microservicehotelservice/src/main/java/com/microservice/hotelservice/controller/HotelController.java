package com.microservice.hotelservice.controller;

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

import com.microservice.hotelservice.entities.Hotel;
import com.microservice.hotelservice.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private HotelService hotelservice;
	
	//create
	
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody  Hotel hotel){
		
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelservice.create(hotel));
	}
	
	
	//get all
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getHotels(){
		
		
		
		return ResponseEntity.status(HttpStatus.OK).body(hotelservice.getAll());
	}
	
	
	//get single

	
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getSingleHotel(@PathVariable  String id){
		
		
		
		return ResponseEntity.status(HttpStatus.OK).body(hotelservice.getSingle(id));
	}
	
}
