package com.worth.postalcodeservice.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import com.worth.postalcodeservice.utils.constants.GeoConstants;

public class GeoUtils {


	public static double distance(double startLat, double startLong, double endLat, double endLong) {

		double dLat = Math.toRadians((endLat - startLat));
		double dLong = Math.toRadians((endLong - startLong));

		startLat = Math.toRadians(startLat);
		endLat = Math.toRadians(endLat);

		double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return BigDecimal.valueOf(GeoConstants.EARTH_RADIUS * c).round(new MathContext(3, RoundingMode.UP)).doubleValue();

	}

	private static double haversin(double val) {
		return Math.pow(Math.sin(val / 2), 2);
	}
}
