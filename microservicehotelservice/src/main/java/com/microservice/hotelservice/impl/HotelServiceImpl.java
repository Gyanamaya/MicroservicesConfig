package com.microservice.hotelservice.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.microservice.hotelservice.entities.Hotel;
import com.microservice.hotelservice.exception.ResourceNotFoundException;
import com.microservice.hotelservice.repository.HotelRepository;
import com.microservice.hotelservice.services.HotelService;


@Service
public class HotelServiceImpl implements HotelService{
	
	
	@Autowired
	private HotelRepository hotelrepo;

	@Override
	public Hotel create(Hotel hotel) {
		// TODO Auto-generated method stub
		
		String hotelid=UUID.randomUUID().toString();
		hotel.setHotelId(hotelid);
		return hotelrepo.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		// TODO Auto-generated method stub
		return hotelrepo.findAll();
	}

	@Override
	public Hotel getSingle(String id) {
		// TODO Auto-generated method stub
		return hotelrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id id not found!!"));
	}

}
