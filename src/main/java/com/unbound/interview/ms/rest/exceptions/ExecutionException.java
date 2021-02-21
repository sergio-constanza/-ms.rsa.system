package com.unbound.interview.ms.rest.exceptions;

public class ExecutionException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private static final String prefix = "Execution failed | ";
	
	public ExecutionException(String message) {
		super(prefix + message);
	}

	public ExecutionException(String message, Exception e) {
		super(prefix + message, e);
	}

}
