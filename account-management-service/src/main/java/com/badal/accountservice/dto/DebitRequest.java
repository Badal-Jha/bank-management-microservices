package com.badal.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebitRequest {
	public Long customerId;
	private String accountNumber;
	private String firstName;
	private String lastName;
	private String Email;
	private Double amount;
}
