package com.worth.postalcodeservice.model;

import lombok.Data;

@Data
public class Location {

	private String postalCode;
	private double latitude;
	private double longitude;
}
