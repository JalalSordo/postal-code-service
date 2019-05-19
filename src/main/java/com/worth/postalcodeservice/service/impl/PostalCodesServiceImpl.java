package com.worth.postalcodeservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.marlonlom.helpers.coordinates.LatsLngs.Exception;
import com.worth.postalcodeservice.dto.LocationDTO;
import com.worth.postalcodeservice.dto.Result;
import com.worth.postalcodeservice.model.Location;
import com.worth.postalcodeservice.repositories.LocationRepository;
import com.worth.postalcodeservice.service.PostalCodesService;
import com.worth.postalcodeservice.utils.GeoUtils;

@Service
public class PostalCodesServiceImpl implements PostalCodesService {

	@Autowired
	private LocationRepository locationRepository;

	@Override
	public Result calculateDistance(String fromPostalCode, String toPostalCode) {

		Result result = new Result();
		Location fromLocation = locationRepository.findOneByPostalCode(fromPostalCode);
		Location toLocation = locationRepository.findOneByPostalCode(toPostalCode);

		if (fromLocation != null && toLocation != null) {
			result.setDistance((float) GeoUtils.distance(fromLocation.getLatitude(), fromLocation.getLongitude(),
					toLocation.getLatitude(), toLocation.getLongitude()));
			try {
				result.setFromLocation(new LocationDTO(fromLocation));
				result.setToLocation(new LocationDTO(toLocation));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return result;
	}

	@Override
	public Location updatePostalCodeCoordinates(Location aLocation) throws LocationNotFoundException {

		Location loadedLocation = locationRepository.findOneByPostalCode(aLocation.getPostalCode());
		if (loadedLocation != null) {
			loadedLocation.setLatitude(aLocation.getLatitude());
			loadedLocation.setLongitude(aLocation.getLongitude());
			return locationRepository.save(loadedLocation);
		} else {
			throw new LocationNotFoundException(aLocation.getPostalCode());
		}

	}

}
