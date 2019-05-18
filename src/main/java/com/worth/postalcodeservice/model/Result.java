
package com.worth.postalcodeservice.model;

import lombok.Data;

@Data
public class Result {

	private Location fromLocation;
	private Location toLocation;
	private float distance;
	private String unit;

}
