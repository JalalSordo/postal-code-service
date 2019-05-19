package com.worth.postalcodeservice.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.worth.postalcodeservice.model.Location;
import com.worth.postalcodeservice.service.impl.LocationNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostalCodesServiceTest {


	@Autowired
	private PostalCodesService postalCodesService;


	@Test
	public void testCalculateDistance() {
			assertEquals(0.806f, postalCodesService.calculateDistance("AB10 1XG", "AB10 6RN").getDistance(), 0.001);
			assertEquals(2.420f, postalCodesService.calculateDistance("AB21 9BE", "AB21 7YS").getDistance(), 0.001);
	}

	@Test
	public void testUpdatePostalCodeCoordinates() throws LocationNotFoundException {
		{

			Location location = new Location("AB21 9HU", 0, 0);
			Location updatedLocation = postalCodesService.updatePostalCodeCoordinates(location);
			assertEquals(location.getPostalCode(),updatedLocation.getPostalCode());
			assertEquals(location.getLatitude(),updatedLocation.getLatitude(),0 );
			assertEquals(location.getLongitude(),updatedLocation.getLongitude(),0 );
		}

		
	}

}
