package com.worth.postalcodeservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.marlonlom.helpers.coordinates.LatsLngs;
import com.github.marlonlom.helpers.coordinates.LatsLngs.Exception;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Location {

	public Location() {
	}
	
	public Location(String postalCode, double latitude, double longitude) {
		this.postalCode = postalCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Getter
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Getter
	@Setter
	private String postalCode;

	@Getter
	@Setter
	@JsonIgnore
	private double latitude;

	@Getter
	@Setter
	@JsonIgnore
	private double longitude;

	@Transient
	private String latitudeInDegrees;

	public String getLatitudeInDegrees() throws Exception {
		return LatsLngs.with(latitude).asLatitude().toDms();
	}

	@Transient
	private String longitudeInDegrees;

	public String getLongitudeInDegrees() throws Exception {
		return LatsLngs.with(longitude).asLongitude().toDms();
	}
}
