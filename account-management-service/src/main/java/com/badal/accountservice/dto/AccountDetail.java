package com.badal.accountservice.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetail {
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String contactNumber;
	private String accountNumber;
	private String AccountType;
	private Double balance;
	private LocalDate dateCreated;

}
