package com.worth.postalcodeservice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.worth.postalcodeservice.model.ApiRequest;

@Repository
public interface ApiRequestRepository extends CrudRepository<ApiRequest, Long> {

}
