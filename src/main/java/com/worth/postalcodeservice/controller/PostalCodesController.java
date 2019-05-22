package com.worth.postalcodeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.worth.postalcodeservice.dto.Result;
import com.worth.postalcodeservice.exceptions.LocationNotFoundException;
import com.worth.postalcodeservice.model.Location;
import com.worth.postalcodeservice.service.PostalCodesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("Main Postal Code Service API endpoint, it supports calculating distance, update and delete a postal code")
@RestController
@RequestMapping("/api/postal-codes")
public class PostalCodesController {

	@Autowired
	private PostalCodesService postalCodesService;

	private static final Logger logger = LoggerFactory.getLogger(PostalCodesController.class);

	@ApiOperation(value = "Returns the geographic (straight line) distance between two postal codes in the UK")
	@GetMapping("/distance")
	public Result calculateDistance( @ApiParam(value = "The \"from\" Postal code parameter", required = true) @RequestParam(required = true) String from,
			@ApiParam(value = "The \"to\" Postal code parameter", required = true) @RequestParam(required = true) String to) {
		logger.debug("-->Executing : calculateDistance");
		logger.debug("from " + from);
		logger.debug("to " + to);

		return postalCodesService.calculateDistance(from, to);
	}

	@PutMapping
	@ResponseBody
	@ApiOperation(value = "Updates postal code coordinates of a location, latitude and longitude must be in decimals.")
	public Location updatePostalCodeCoordinates(@ApiParam(value = "The Location object to update", required = true) @RequestBody(required = true) Location location)
			throws LocationNotFoundException {
		logger.debug("-->Executing : updatePostalCodeCoordinates");
		logger.debug("location " + location.toString());
		return postalCodesService.updatePostalCodeCoordinates(location);
	}

	@DeleteMapping
	@ApiOperation(value = "Deletes a postal code along with their latitude and longitude")
	public void deletePostalCode(@ApiParam(value = "The postal code of the location to delete", required = true) @RequestBody(required = true) String postalCode) throws LocationNotFoundException {
		logger.debug("-->Executing : deletePostalCode");
		logger.debug("postalCode " + postalCode);
		postalCodesService.deleteLocationByPostalCode(postalCode);
	}

}
