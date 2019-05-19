package com.worth.postalcodeservice.service;

import com.worth.postalcodeservice.dto.Result;
import com.worth.postalcodeservice.model.Location;
import com.worth.postalcodeservice.service.impl.LocationNotFoundException;


public interface PostalCodesService {

	public Result calculateDistance(String from, String to);

	public Location updatePostalCodeCoordinates(Location location) throws LocationNotFoundException;

}
