package com.worth.postalcodeservice.service;

import org.springframework.stereotype.Service;

import com.worth.postalcodeservice.model.ApiRequest;

@Service
public interface ApiRequestsService {

	public void save(ApiRequest requestModel);

}
