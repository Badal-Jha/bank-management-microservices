package com.nagarro.accountservice.exceptions;

public class CustomException extends RuntimeException {

	private final String message;
	private final int code;

	public CustomException(String message, int code) {
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}
}