package com.worth.postalcodeservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
public class ApiRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private long id;
	private String fromPostalCode;
	private String toPostalCode;

	@Setter(AccessLevel.NONE)
	private Date timestamp = new Date();

}
