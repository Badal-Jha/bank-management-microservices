package com.badal.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String contactNumber;
	private String accountNumber;

}
