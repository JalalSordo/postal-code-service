package com.worth.postalcodeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.worth.postalcodeservice.model.Result;
import com.worth.postalcodeservice.service.PostalCodesService;

@RestController
@RequestMapping("/api/postal-codes")
public class PostalCodesController {

	@Autowired
	private PostalCodesService postalCodesService;
	
	private static final Logger logger = LoggerFactory.getLogger(PostalCodesController.class);

	@GetMapping("/distance")
	public Result calculateDistance(
			@RequestParam(required = true) String from,
			@RequestParam(required = true) String to) {
		logger.info("from " + from);
		logger.info("to " + to);
		
		
		return postalCodesService.calculateDistance(from,to);
	}

}
