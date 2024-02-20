package com.microservice.userservice.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.logging.LogFactory;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.userservice.entities.Hotel;
import com.microservice.userservice.entities.Rating;
import com.microservice.userservice.entities.User;
import com.microservice.userservice.exceptions.ResourceNotFoundException;
import com.microservice.userservice.external.services.HotelService;
import com.microservice.userservice.repository.UserRepository;
import com.microservice.userservice.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelservice;
	

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		
		String randomUserId= UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userrepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userrepository.findAll();
		
		
	}

	@Override
	public User getUser(String userid) {
		// TODO Auto-generated method stub
		
	User user=userrepository.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found in system." +userid));
	
	//fetch rating from rating service
	//http://localhost:8383/ratings/userid/7b7670de-9111-49d1-82b1-5858eb74cd5f
	
	Rating[] ratingOfUser= restTemplate.getForObject("http://RATING-SERVICE/ratings/userid/"+user.getUserId(), Rating[].class);
	
	List<Rating> ratingListWithoutHotels=Arrays.stream(ratingOfUser).toList();
	System.out.println("http://localhost:8383/ratings/userid/"+user.getUserId());
	
	List<Rating> ratingList=ratingListWithoutHotels.stream().map(rating -> {
		
		//rest template usage
		//api call to hotel service to get the hotel//localhost:8282/hotels/003ab581-1524-4cd8-b34f-008b55ef96a5
		
		//ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
		
		//Hotel hotel=forEntity.getBody();
		//System.out.println("http://localhost:8282/hotels/"+rating.getHotelId());
		
		//feign client usage
		
		Hotel hotel=hotelservice.getHotel(rating.getHotelId());
		rating.setHotel(hotel);
		return rating;
		//set the hotel to rating
		
		//return the rating
		
		
	}).collect(Collectors.toList());

	
	
	user.setRatings(ratingList);
	return user;
	}

}
