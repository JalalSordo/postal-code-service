
package com.worth.postalcodeservice.dto;

import com.worth.postalcodeservice.utils.constants.GeoConstants;

import lombok.Data;

@Data
public class Result {

	private LocationDTO fromLocation;
	private LocationDTO toLocation;
	private float distance;
	private String unit = GeoConstants.DISTANCE_UNIT;

}
