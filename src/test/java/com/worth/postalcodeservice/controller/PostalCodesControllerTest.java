package com.worth.postalcodeservice.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.worth.postalcodeservice.model.Location;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostalCodesControllerTest {

	
	private static final String PostalCode_ENDPOINT = "/api/postal-codes";
	private static final String CalculateDistance_ENDPOINT = PostalCode_ENDPOINT+"/distance";

	@Autowired
	private MockMvc mvc;

	@Test
	public void testCalculateDistance() throws Exception {

		mvc.perform(get(CalculateDistance_ENDPOINT)//
				.contentType(MediaType.APPLICATION_JSON))//
				.andExpect(status().isBadRequest());

		mvc.perform(get(CalculateDistance_ENDPOINT)//
				.param("from", "")//
				.param("to", "")//
				.contentType(MediaType.APPLICATION_JSON))//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.fromLocation").value(IsNull.nullValue()))//
				.andExpect(jsonPath("$.toLocation").value(IsNull.nullValue()))//
				.andExpect(jsonPath("$.distance", is(0.0)))//
				.andExpect(jsonPath("$.unit", is("km")));

		mvc.perform(get(CalculateDistance_ENDPOINT)//
				.param("from", "AB10 1XG")//
				.param("to", "AB10 6RN")//
				.contentType(MediaType.APPLICATION_JSON))//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.fromLocation.postalCode", is("AB10 1XG")))//
				.andExpect(jsonPath("$.fromLocation.latitude", is("57°8'38\" N")))//
				.andExpect(jsonPath("$.fromLocation.longitude", is("2°6'53\" W")))//
				.andExpect(jsonPath("$.toLocation.postalCode", is("AB10 6RN")))//
				.andExpect(jsonPath("$.toLocation.latitude", is("57°8'16\" N")))//
				.andExpect(jsonPath("$.toLocation.longitude", is("2°7'17\" W")))//
				.andExpect(jsonPath("$.distance", is(0.806)))//
				.andExpect(jsonPath("$.unit", is("km")));

	}

	@Test
	public void testUpdatePostalCodeCoordinates() throws Exception {

		mvc.perform(put(PostalCode_ENDPOINT)//
				.contentType(MediaType.APPLICATION_JSON))//
				.andExpect(status().isBadRequest());

		mvc.perform(put(PostalCode_ENDPOINT)//
				.content(asJsonString(new Location()))//
				.contentType(MediaType.APPLICATION_JSON))//
				.andExpect(status().isNotFound());

		Location location = new Location();
		location.setPostalCode("AB21 9BJ");
		location.setLatitude(0);
		location.setLongitude(0);

		mvc.perform(put(PostalCode_ENDPOINT)//
				.content(asJsonString(location))//
				.contentType(MediaType.APPLICATION_JSON))//
				.andExpect(jsonPath("$.postalCode", is("AB21 9BJ")))//
				.andExpect(jsonPath("$.latitude", is(0.0)))//
				.andExpect(jsonPath("$.longitude", is(0.0)))//
				.andExpect(status().isOk());

	}

	@Test
	public void testDeletePostalCode() throws Exception {

		mvc.perform(delete(PostalCode_ENDPOINT)//
				.contentType(MediaType.APPLICATION_JSON))//
				.andExpect(status().isBadRequest());

		mvc.perform(delete(PostalCode_ENDPOINT)//
				.content("XXXX")//
				.contentType(MediaType.APPLICATION_JSON))//
				.andExpect(status().isNotFound());
		
		mvc.perform(delete(PostalCode_ENDPOINT)//
				.content("AB21 7AR")//
				.contentType(MediaType.APPLICATION_JSON))//
				.andExpect(status().isOk());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
