package com.unbound.interview.ms.rest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.unbound.interview.ms.model.ResponseContext;
import com.unbound.interview.ms.rest.exceptions.DBException;
import com.unbound.interview.ms.rest.exceptions.ExecutionException;
import com.unbound.interview.ms.rest.exceptions.NotFoundException;
import com.unbound.interview.ms.rest.exceptions.RSAException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class RSAControllerAdvise { 
	private static final Logger logger = LogManager.getLogger(RSAControllerAdvise.class);
	
	@ResponseBody
	@ExceptionHandler(ExecutionException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	ResponseContext apiHandler(ExecutionException e) {
		logger.error("Execution failed", e);
		
		return buildErrorResponseContext(e);
	}

	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ResponseContext apiHandler(NotFoundException e) {
		logger.error("Data not found", e);
		
		return buildErrorResponseContext(e);
	}
	
	@ResponseBody
	@ExceptionHandler(DBException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	ResponseContext apiHandler(DBException e) {
		logger.error("DB storage failed", e);
		
		return buildErrorResponseContext(e);
	}
	
	@ResponseBody
	@ExceptionHandler(RSAException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	ResponseContext apiHandler(RSAException e) {
		logger.error("RSA mechanizm failed", e);
		
		return buildErrorResponseContext(e);
	}

	private ResponseContext buildErrorResponseContext(Exception e) {
		ResponseContext responseContext = new ResponseContext();
		
		responseContext.setStatus("FAILURE");
		responseContext.setDescription(e.getMessage());
		
		return responseContext;
	}
}
