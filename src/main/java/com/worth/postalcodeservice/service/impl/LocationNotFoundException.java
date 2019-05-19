package com.worth.postalcodeservice.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class LocationNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public LocationNotFoundException(String postalCode) {
		super("Coudl not find the specified postalCode : "+postalCode);
	}
}
