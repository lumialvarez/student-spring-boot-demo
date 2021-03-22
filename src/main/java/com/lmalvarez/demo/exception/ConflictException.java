package com.lmalvarez.demo.exception;

public class ConflictException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5836285165535342191L;

	public ConflictException(String message) {
		super(message);
	}
	
	public ConflictException(String message, Throwable cause) {
		super(message, cause);
	}
}
