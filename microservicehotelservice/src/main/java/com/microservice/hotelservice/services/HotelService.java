package com.microservice.hotelservice.services;

import java.util.List;

import com.microservice.hotelservice.entities.Hotel;

public interface HotelService {
	
	
	
	//create
	
	
	Hotel create(Hotel hotel);
	
	
	//get all
	
	List<Hotel> getAll();
	
	
	
	//get single
	
	Hotel getSingle(String id);

}
