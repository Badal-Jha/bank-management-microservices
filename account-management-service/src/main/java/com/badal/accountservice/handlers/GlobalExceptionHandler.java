package com.badal.accountservice.handlers;

import java.time.Instant;

import com.badal.accountservice.dto.ErrorResponse;
import com.badal.accountservice.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {

		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getCode(), Instant.now());
		return ResponseEntity.status(HttpStatus.valueOf(e.getCode())).body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e) {

		System.out.println("error " + e.getMessage());

		ErrorResponse errorResponse = new ErrorResponse("Internal Server Error",
				HttpStatus.INTERNAL_SERVER_ERROR.value(), Instant.now());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
}
