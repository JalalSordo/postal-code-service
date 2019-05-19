package com.worth.postalcodeservice.service;

import com.worth.postalcodeservice.model.Result;


public interface PostalCodesService {

	public Result calculateDistance(String from, String to);

}
