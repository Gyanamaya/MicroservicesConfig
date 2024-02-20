package com.microservice.userservice.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	
	public ResourceNotFoundException() {
		
		super("Resource not found in server !!");
	}
	
	
	public ResourceNotFoundException(String msg) {
		
		
		super(msg);
	}
}
