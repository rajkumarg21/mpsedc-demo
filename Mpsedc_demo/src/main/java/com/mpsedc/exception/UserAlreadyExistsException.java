package com.mpsedc.exception;

public class UserAlreadyExistsException extends RuntimeException {
	public UserAlreadyExistsException(String message) {
		super(message);
		this.getMessage();

	}
}
