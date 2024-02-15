package com.nagarro.accountservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.accountservice.dto.AccountDetail;
import com.nagarro.accountservice.dto.AccountDto;
import com.nagarro.accountservice.dto.CreditRequest;
import com.nagarro.accountservice.dto.CreditResponse;
import com.nagarro.accountservice.dto.DebitRequest;
import com.nagarro.accountservice.dto.DebitResponse;
import com.nagarro.accountservice.exceptions.CustomException;
import com.nagarro.accountservice.services.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

	private final AccountService accountService;

	// create account
	@PostMapping("/{customerId}")
	public ResponseEntity<String> createAccount(@PathVariable("customerId") Long customerId,
			@RequestBody AccountDto accountDto) {

		String accountNumber = accountService.addAccount(accountDto, customerId);
		return ResponseEntity.status(HttpStatus.CREATED).body("account number: " + accountNumber);
	}

	// add money to account
	@PutMapping("/credit")
	public ResponseEntity<CreditResponse> addAmount(@RequestBody CreditRequest creditRequest) {
		if (creditRequest.getAmount() < 0) {
			throw new CustomException("amount can not be negative", 400);
		}

		return ResponseEntity.status(HttpStatus.OK).body(accountService.addMoney(creditRequest));
	}

	// debit money
	@PutMapping("/debit")
	public ResponseEntity<DebitResponse> withdrawAmount(@RequestBody DebitRequest debitRequest) {
		if (debitRequest.getAmount() < 0) {
			throw new CustomException("amount can not be negative", 400);
		}
		return ResponseEntity.status(HttpStatus.OK).body(accountService.withdrawAmount(debitRequest));
	}

	// get Account Details
	@GetMapping("/{customerId}/{accountNumber}")
	public ResponseEntity<AccountDetail> getAccountDetails(@PathVariable("customerId") Long customerId,
			@PathVariable("accountNumber") String accountNumber) {
		return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountDetails(customerId, accountNumber));
	}

	// delete account
	@DeleteMapping("/{accountNumber}")
	public ResponseEntity<String> deleteAccount(@PathVariable("accountNumber") String accountNumber) {
		this.accountService.deleteAccount(accountNumber);

		return ResponseEntity.status(HttpStatus.OK).body("account Number: " + accountNumber + " deleted");
	}

	// delete all accounts having given customerId
	@DeleteMapping("/customer/{id}")
	public void deleteAccounts(@PathVariable("id") Long customerId) {
		this.accountService.deleteAccountsById(customerId);
	}
}
