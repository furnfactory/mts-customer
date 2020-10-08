/*
 * Created on 08-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtscustomer.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author murugan
 *
 */
@Slf4j
@ControllerAdvice
public class MvcExceptionHandler {

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

	// TODO - Know the between ConstraintViolation & MethodArgumentNotValid
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException ex) {
		List<String> validationErrors = new ArrayList<>(ex.getConstraintViolations().size());
		ex.getConstraintViolations().forEach(violation -> {
			validationErrors.add(violation.getPropertyPath() + "_" + violation.getMessage());
		});
		return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
	}

}
