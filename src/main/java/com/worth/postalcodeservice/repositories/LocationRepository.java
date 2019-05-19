package com.worth.postalcodeservice.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.worth.postalcodeservice.model.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long>{
	
	@Query("from Location l where l.postalCode=:postalCode")
	public Location findOneByPostalCode(@Param(value = "postalCode") String postalCode);



}
