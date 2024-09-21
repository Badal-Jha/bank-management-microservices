package com.badal.accountservice.entities;

import java.time.LocalDate;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Account {

	@Id
	private String accountNumber;
	private String AccountType;
	private Double balance;
	private LocalDate dateCreated;
	private Long customerId;

	public Account() {

		this.dateCreated = LocalDate.now();
		this.accountNumber = generateAccountNumber();
		balance = 0.0;
	}

	public String generateAccountNumber() {
		Random random = new Random();
		StringBuilder accountNumber = new StringBuilder();

		for (int i = 0; i < 10; i++) {
			accountNumber.append((char) ('0' + random.nextInt(10)));
		}

		return accountNumber.toString();
	}

}
