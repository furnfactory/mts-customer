/*
 * Created on 05-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtscustomer.services;

import java.util.UUID;

import com.tamil.mts.mtscustomer.web.model.CustomerDto;

/**
 * @author murugan
 *
 */
public interface CustomerService {

	CustomerDto getCustomerById(UUID customerId);

	CustomerDto saveNewCustomer(CustomerDto customerDto);
	
	void updateCustomer(UUID customerId, CustomerDto customerDto);

	void deleteCustomerById(UUID customerId);
	
}
