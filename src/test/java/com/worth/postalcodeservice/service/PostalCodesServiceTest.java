package com.worth.postalcodeservice.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.worth.postalcodeservice.model.Location;
import com.worth.postalcodeservice.repositories.PostalCodesRepository;
import com.worth.postalcodeservice.service.impl.PostalCodesServiceImpl;

@RunWith(SpringRunner.class)
public class PostalCodesServiceTest {

	@TestConfiguration
	static class setUpConfiguration {

		@Bean
		public PostalCodesService getPostalCodesService() {
			return new PostalCodesServiceImpl();
		}
	}

	@Autowired
	private PostalCodesService postalCodesService;

	@MockBean
	PostalCodesRepository postalCodesRepository;

	@Test
	public void testCalculateDistance() {
		{

			Location fromLocation = new Location("AB10 1XG", 57.144165160000000, -2.114847768000000);
			Location toLocation = new Location("AB10 6RN", 57.137879760000000, -2.121486688000000);
			when(postalCodesRepository.getLocationByPostalCode("AB10 1XG")).thenReturn(fromLocation);
			when(postalCodesRepository.getLocationByPostalCode("AB10 6RN")).thenReturn(toLocation);
			assertEquals(0.806f, postalCodesService.calculateDistance("AB10 1XG", "AB10 6RN").getDistance(), 0.001);
		}

		{
			Location fromLocation = new Location("M25 9XS", 53.516028653899800, -2.284310481252200);
			Location toLocation = new Location("AB21 7YS", 57.199811860000000, -2.190885191000000);
			when(postalCodesRepository.getLocationByPostalCode("M25 9XS")).thenReturn(fromLocation);
			when(postalCodesRepository.getLocationByPostalCode("AB21 7YS")).thenReturn(toLocation);
			assertEquals(410.0, postalCodesService.calculateDistance("M25 9XS", "AB21 7YS").getDistance(), 0.001);
		}
	}

}
