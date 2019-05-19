package com.worth.postalcodeservice.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worth.postalcodeservice.model.Location;
import com.worth.postalcodeservice.model.Result;
import com.worth.postalcodeservice.repositories.PostalCodesRepository;
import com.worth.postalcodeservice.service.PostalCodesService;

@Service
public class PostalCodesServiceImpl implements PostalCodesService {

	@Autowired
	private PostalCodesRepository postalCodesRepository;

	private static final int EARTH_RADIUS = 6372; // Approx Earth radius in KM

	@Override
	public Result calculateDistance(String fromPostalCode, String toPostalCode) {
		Result result = new Result();
		Location fromLocation = postalCodesRepository.getLocationByPostalCode(fromPostalCode);
		Location toLocation = postalCodesRepository.getLocationByPostalCode(toPostalCode);

		// double dLat = Math.toRadians(lat2 - lat1);
		if (fromLocation != null && toLocation != null) {
			result.setDistance((float) distance(fromLocation.getLatitude(), fromLocation.getLongitude(),
					toLocation.getLatitude(), toLocation.getLongitude()));

			result.setFromLocation(fromLocation);
			result.setToLocation(toLocation);
		}
		return result;
	}

	public double distance(double startLat, double startLong, double endLat, double endLong) {

		double dLat = Math.toRadians((endLat - startLat));
		double dLong = Math.toRadians((endLong - startLong));

		startLat = Math.toRadians(startLat);
		endLat = Math.toRadians(endLat);

		double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));   
		
		return BigDecimal.valueOf(EARTH_RADIUS * c).round(new MathContext(3, RoundingMode.UP)).doubleValue();
		//return Math.floor(EARTH_RADIUS * c * 1000) / 1000;
	}

	public double haversin(double val) {
		return Math.pow(Math.sin(val / 2), 2);
	}

}
