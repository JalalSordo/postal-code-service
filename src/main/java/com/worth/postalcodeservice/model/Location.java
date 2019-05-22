package com.worth.postalcodeservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@ToString
public class Location {

	public Location() {
	}

	public Location(String postalCode, double latitude, double longitude) {
		this.postalCode = postalCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	@JsonIgnore
	private long id;
	
	@Column(unique=true)
	private String postalCode;

	private double latitude;

	private double longitude;
	
	

}
