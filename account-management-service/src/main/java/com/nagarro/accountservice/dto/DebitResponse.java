package com.nagarro.accountservice.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebitResponse {
	private String accountNumber;
	private Double amountDebited;
	private LocalDate date;
}
