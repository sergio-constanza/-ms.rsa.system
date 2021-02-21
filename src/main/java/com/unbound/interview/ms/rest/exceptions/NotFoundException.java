package com.unbound.interview.ms.rest.exceptions;

public class NotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private static final String prefix = "Entity not found | ";
	
	public NotFoundException(String message) {
		super(prefix + message);
	}

	public NotFoundException(String message, Exception e) {
		super(prefix + message, e);
	}

}
