package com.unbound.interview.ms.rest.exceptions;

public class DBException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private static final String prefix = "DB procedure failed | ";
	
	public DBException(String message) {
		super(prefix + message);
	}

	public DBException(String message, Exception e) {
		super(prefix + message, e);
	}

}
