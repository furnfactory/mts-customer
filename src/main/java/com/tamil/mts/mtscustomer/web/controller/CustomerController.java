/*
 * Created on 05-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtscustomer.web.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamil.mts.mtscustomer.services.CustomerService;
import com.tamil.mts.mtscustomer.web.model.CustomerDto;

/**
 * @author murugan
 *
 */
@RequestMapping("api/v1/customer")
@RestController
public class CustomerController {

	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
	
	@GetMapping({"/{customerId}"})
	public ResponseEntity<CustomerDto> getEmployeeById(@PathVariable("customerId") UUID customerId) {
		 return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
	}
	
}
