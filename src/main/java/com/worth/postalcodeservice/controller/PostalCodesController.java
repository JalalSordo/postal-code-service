package com.worth.postalcodeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worth.postalcodeservice.model.Result;

@RestController
@RequestMapping("/api/postal-codes")
public class PostalCodesController {

	@GetMapping("/distance")
	public Result calculateDistance() {
		return new Result();
	}

}
