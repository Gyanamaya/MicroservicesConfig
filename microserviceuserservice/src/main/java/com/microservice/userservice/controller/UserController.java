package com.microservice.userservice.controller;

import java.util.List;
import java.util.logging.Logger;

//import org.hibernate.boot.jaxb.JaxbLogger_.logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.userservice.entities.User;
import com.microservice.userservice.payload.ApiResponse;
import com.microservice.userservice.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	//create
	@PostMapping
	public ResponseEntity<User>  createUser(@RequestBody User user) {
		
		User user1=userservice.saveUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
		
	}
	
	/*
	 * 
	 * for retry, first attemps will happen and then fallbackmethod will be executed.
	 * 
	 * 
	 */
	
	
	int retryCount=1;
	//single user get
	@GetMapping("/{userid}")
	//@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallBack")
	//@Retry(name="ratingHotelService",fallbackMethod = "ratingHotelFallBack")
	@RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User>  getSingleUser(@PathVariable String userid) {
		
		System.out.println("retry count: {}" + retryCount);
		retryCount++;
		
		User user1=userservice.getUser(userid);
		
		return ResponseEntity.ok(user1);
		
	}
	
	
	//creating fall back method for circuit breaker
	public ResponseEntity<User>  ratingHotelFallBack(String userid,Exception ex) {
		
		
		System.out.println("Fallback method is exceuted because service is down"+ex.getMessage());
		
		
		User user=User.builder()
		    .userEmail("dummy@gmail.com")
		    .userName("dummy")
		    .userAbout("This user is created dummy because some service is down")
		    .userId("14432")
		    .build();
		
		
		
		return ResponseEntity.ok(user);
	}
	
	//all user get
	
	@GetMapping
	public ResponseEntity<List<User>>  getAllUser() {
		
		List<User> userlist=userservice.getAllUser();
		
		return ResponseEntity.ok(userlist);
		
	}
}
