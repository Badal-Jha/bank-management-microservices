package com.badal.accountservice.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditResponse {
	private String accountNumber;
	private Double amountCredited;
	private LocalDate date;
}
