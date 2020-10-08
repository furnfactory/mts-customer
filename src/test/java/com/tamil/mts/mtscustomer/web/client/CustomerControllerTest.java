/*
 * Created on 08-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtscustomer.web.client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tamil.mts.mtscustomer.services.CustomerService;
import com.tamil.mts.mtscustomer.web.controller.CustomerController;
import com.tamil.mts.mtscustomer.web.model.CustomerDto;

/**
 * @author murugan
 *
 */
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	CustomerService customerService;

	private final String CUSTOMER_API_PATH = "/api/v1/customer/";
	
	@Test
	public void getCustomerById() throws Exception {
		given(customerService.getCustomerById(any(UUID.class))).willReturn(getValidCustomerDto());
		mockMvc.perform(get(CUSTOMER_API_PATH + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void createValidCustomer() throws Exception {
		CustomerDto employeeDto = getNewCustomerDto();
		String employeeDtoJson = objectMapper.writeValueAsString(employeeDto);
		given(customerService.saveNewCustomer(any(CustomerDto.class))).willReturn(getValidCustomerDto());
		mockMvc.perform(post(CUSTOMER_API_PATH).contentType(MediaType.APPLICATION_JSON).content(employeeDtoJson))
				.andExpect(status().isCreated());
	}

	@Test
	public void createInvalidCustomer() throws Exception {
		CustomerDto employeeDto = getInvalidCustomerDto();
		String employeeDtoJson = objectMapper.writeValueAsString(employeeDto);
		given(customerService.saveNewCustomer(any(CustomerDto.class))).willReturn(getValidCustomerDto());
		mockMvc.perform(post(CUSTOMER_API_PATH).contentType(MediaType.APPLICATION_JSON).content(employeeDtoJson))
				.andExpect(status().is4xxClientError());
	}
	
	private CustomerDto getValidCustomerDto() {
		return CustomerDto.builder().id(UUID.randomUUID()).name("TestCustomer").age(24).build();
	}
	
	private CustomerDto getNewCustomerDto() {
		return CustomerDto.builder().name("Test").age(24).build();
	}
	
	private CustomerDto getInvalidCustomerDto() {
		return CustomerDto.builder().id(UUID.randomUUID()).name("").age(5).build();
	}
}
