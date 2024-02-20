package com.microservice.userservice.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder


@Entity
@Table(name= "micro_users")
public class User {
	
	@Id
	@Column(name="ID")
	private String userId;
	@Column(name="NAME", length = 20)
	private String userName;
	@Column(name="EMAIL")
	private String userEmail;
	@Column(name="ABOUT")
	private String userAbout;
	
	
	@Transient
	private List<Rating> ratings=new ArrayList<Rating>();


}
