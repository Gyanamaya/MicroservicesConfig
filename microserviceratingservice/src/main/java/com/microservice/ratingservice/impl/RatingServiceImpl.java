package com.microservice.ratingservice.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.ratingservice.entities.Rating;
import com.microservice.ratingservice.repository.RatingRepository;
import com.microservice.ratingservice.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	private RatingRepository ratingrepo;

	@Override
	public Rating createRating(Rating rating) {
		// TODO Auto-generated method stub
		String ratingid=UUID.randomUUID().toString();
		rating.setRatingId(ratingid);
		return ratingrepo.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		// TODO Auto-generated method stub
		return ratingrepo.findAll();
	}

	@Override
	public List<Rating> getAllRatingByUserId(String userid) {
		// TODO Auto-generated method stub
		return ratingrepo.findByUserId(userid);
	}

	@Override
	public List<Rating> getAllRatingByHotelId(String hotelid) {
		// TODO Auto-generated method stub
		return ratingrepo.findByHotelId(hotelid);
	}

}
