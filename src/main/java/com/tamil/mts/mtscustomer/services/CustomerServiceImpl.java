/*
 * Created on 05-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtscustomer.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tamil.mts.mtscustomer.web.model.CustomerDto;

import lombok.extern.slf4j.Slf4j;

/**
 * @author murugan
 *
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto getCustomerById(UUID customerId) {
		return CustomerDto.builder().id(UUID.randomUUID()).name("Senthamizh").age(28).build();
	}

	@Override
	public CustomerDto saveNewCustomer(CustomerDto customerDto) {
		return CustomerDto.builder().id(UUID.randomUUID()).build();
	}

	@Override
	public void updateCustomer(UUID customerId, CustomerDto customerDto) {
		// TODO Implement update Customer
		log.info("TODO: Implement update Customer");
	}

	@Override
	public void deleteCustomerById(UUID customerId) {
		// TODO Implement update Customer
		log.info("TODO: Implement delete Customer");
	}

}
