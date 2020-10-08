/*
 * Created on 05-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtscustomer.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tamil.mts.mtscustomer.services.CustomerService;
import com.tamil.mts.mtscustomer.web.model.CustomerDto;

import lombok.extern.slf4j.Slf4j;

/**
 * @author murugan
 *
 */
@Slf4j
@RequestMapping("api/v1/customer")
@RestController
@Validated
public class CustomerController {

	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
	
	@GetMapping({"/{customerId}"})
	public ResponseEntity<CustomerDto> getEmployeeById(@NotNull @PathVariable("customerId") UUID customerId) {
		 return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping
	public ResponseEntity createEmployee(@Valid @RequestBody CustomerDto customerDto) {
		CustomerDto savedCustomerDto = customerService.saveNewCustomer(customerDto);
		HttpHeaders headers = new HttpHeaders();
		headers.add("savedCustomerURI", "api/v1/customer/" + savedCustomerDto.getId().toString());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@SuppressWarnings({ "rawtypes"})
	@PutMapping({"/{customerId}"})
    public ResponseEntity updateEmployee(@NotNull @PathVariable("customerId") UUID customerId, @Valid @RequestBody CustomerDto customerDto) {
		customerService.updateCustomer(customerId, customerDto);
		return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@DeleteMapping({"/{customerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@NotNull @PathVariable("customerId") UUID customerId){
		customerService.deleteCustomerById(customerId);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> validationErrorHandler(MethodArgumentNotValidException ex) {
		List<String> validationErrors = new ArrayList<>(ex.getBindingResult().getErrorCount());
		ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
			log.warn("Dto: {}, Invalid Request: {} = {},  Message: {}", fieldError.getObjectName(),
					fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
			validationErrors.add(String.format("%s = %s, message: %s", fieldError.getField(),
					fieldError.getRejectedValue(), fieldError.getDefaultMessage()));
		});
		return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
	}
}
