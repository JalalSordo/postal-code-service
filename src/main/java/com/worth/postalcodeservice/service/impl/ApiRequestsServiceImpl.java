package com.worth.postalcodeservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worth.postalcodeservice.model.ApiRequest;
import com.worth.postalcodeservice.repositories.ApiRequestRepository;
import com.worth.postalcodeservice.service.ApiRequestsService;

@Service
public class ApiRequestsServiceImpl implements ApiRequestsService {

	@Autowired
	private ApiRequestRepository apiRequestRepository;

	@Override
	public void save(ApiRequest requestModel) {
		apiRequestRepository.save(requestModel);

	}

}
