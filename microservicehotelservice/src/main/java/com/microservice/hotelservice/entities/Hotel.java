package com.microservice.hotelservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


@Entity
@Table(name="micro_hotels")
public class Hotel {
	
	@Id
	@Column(name="ID")
	private String hotelId;
	@Column(name="NAME")
	private String hotelName;
	@Column(name="LOCATION")
	private String hotelLocation;
	@Column(name="ABOUT")
	private String hotelAbout;

}
