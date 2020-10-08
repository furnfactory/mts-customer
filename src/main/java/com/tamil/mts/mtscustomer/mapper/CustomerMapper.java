/*
 * Created on 09-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtscustomer.mapper;

import org.mapstruct.Mapper;

import com.tamil.mts.mtscustomer.domain.Customer;
import com.tamil.mts.mtscustomer.web.model.CustomerDto;

/**
 * @author murugan
 *
 */
@Mapper
public interface CustomerMapper {

	CustomerDto convertToModel(Customer customer);

	Customer converToDomain(CustomerDto customerDto);
	
}
