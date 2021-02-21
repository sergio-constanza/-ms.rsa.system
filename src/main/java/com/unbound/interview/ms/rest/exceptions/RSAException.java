package com.unbound.interview.ms.rest.exceptions;

public class RSAException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private static final String prefix = "Execution failed | ";
	
	public RSAException(String message) {
		super(prefix + message);
	}

	public RSAException(String message, Exception e) {
		super(prefix + message, e);
	}

}
