package com.worth.postalcodeservice.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)

@SpringBootTest
@AutoConfigureMockMvc
public class PostalCodesControllerTest {
	
	
	
	@Autowired
	private MockMvc mvc;
	
//    @MockBean
//    private PostalCodesService postalCodesService;

	// write test cases here

	

	@Test
	public void testCalculateDistance() throws Exception {

	
		
//		Result result = new Result();
//		result.setDistance(distance);
//		result.setFromLocation(fromLocation);
//		result.setToLocation(toLocation);
//		
//
//		when(postalCodesService.calculateDistance("AB10 1XG", "AB10 6RN")).thenReturn(result);
//		
		mvc.perform(get("/api/postal-codes/distance")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
		
		
		mvc.perform(get("/api/postal-codes/distance")
				.param("from", "")
				.param("to", "")
				.contentType(MediaType.APPLICATION_JSON))
		  		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.fromLocation").value(IsNull.nullValue()))
				.andExpect(jsonPath("$.toLocation").value(IsNull.nullValue()))
				.andExpect(jsonPath("$.distance", is(0.0)))
				.andExpect(jsonPath("$.unit", is("km")))
				.andExpect(status().isOk());
		
		mvc.perform(get("/api/postal-codes/distance")
				.param("from", "AB10 1XG")
				.param("to", "AB10 6RN")
				.contentType(MediaType.APPLICATION_JSON))
		  		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.fromLocation.postalCode", is("AB10 1XG")))
				.andExpect(jsonPath("$.fromLocation.latitudeInDegrees", is("57°8'38\" N")))
				.andExpect(jsonPath("$.fromLocation.longitudeInDegrees", is("2°6'53\" W")))
				.andExpect(jsonPath("$.toLocation.postalCode", is("AB10 6RN")))
				.andExpect(jsonPath("$.toLocation.latitudeInDegrees", is("57°8'16\" N")))
				.andExpect(jsonPath("$.toLocation.longitudeInDegrees", is("2°7'17\" W")))
				.andExpect(jsonPath("$.distance", is(0.806)))
				.andExpect(jsonPath("$.unit", is("km")))
				.andExpect(status().isOk());
		
		
	}
}
