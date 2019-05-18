package com.worth.postalcodeservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(PostalCodesController.class)
public class PostalCodesControllerTest {

	@Autowired
	private MockMvc mvc;

//    @MockBean
//    private EmployeeService service;

	// write test cases here

	@Test
	public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {

//		Employee alex = new Employee("alex");
//
//		List<Employee> allEmployees = Arrays.asList(alex);
//
//		given(service.getAllEmployees()).willReturn(allEmployees);

		mvc.perform(get("/api/postal-codes/distance").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
