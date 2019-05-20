package com.worth.postalcodeservice.service;

import com.worth.postalcodeservice.dto.Result;
import com.worth.postalcodeservice.exceptions.LocationNotFoundException;
import com.worth.postalcodeservice.model.Location;


public interface PostalCodesService {

	public Result calculateDistance(String from, String to);

	public Location updatePostalCodeCoordinates(Location location) throws LocationNotFoundException;
	
	public Location findOneByPostalCode(String postalCode) throws LocationNotFoundException;

	public void deleteLocationByPostalCode(String postalCode) throws LocationNotFoundException;

}
