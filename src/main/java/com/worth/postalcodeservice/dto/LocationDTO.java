package com.worth.postalcodeservice.dto;

import com.github.marlonlom.helpers.coordinates.LatsLngs;
import com.github.marlonlom.helpers.coordinates.LatsLngs.Exception;
import com.worth.postalcodeservice.model.Location;

import lombok.Data;

@Data
public class LocationDTO {

	public LocationDTO() {
	}

	public LocationDTO(Location location) throws Exception {
		if (location != null) {
			postalCode = location.getPostalCode();
			latitude = LatsLngs.with(location.getLatitude()).asLatitude().toDms();
			longitude = LatsLngs.with(location.getLongitude()).asLongitude().toDms();
		}

	}

	private String postalCode;

	private String latitude;

	private String longitude;

}
