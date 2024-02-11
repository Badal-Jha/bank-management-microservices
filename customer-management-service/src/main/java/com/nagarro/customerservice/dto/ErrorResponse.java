package com.nagarro.customerservice.dto;

import java.time.Instant;

public class ErrorResponse {

	private final String message;
	private final int code;
	private final Instant timestamp;

	public ErrorResponse(String message, int code, Instant timestamp) {
		this.message = message;
		this.code = code;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public Instant getTimestamp() {
		return timestamp;
	}
}
