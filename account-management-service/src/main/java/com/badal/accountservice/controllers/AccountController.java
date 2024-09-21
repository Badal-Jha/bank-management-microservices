package com.badal.accountservice.controllers;



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

import com.badal.accountservice.dto.AccountDetail;
import com.badal.accountservice.dto.AccountDto;
import com.badal.accountservice.dto.CreditRequest;
import com.badal.accountservice.dto.CreditResponse;
import com.badal.accountservice.dto.DebitRequest;
import com.badal.accountservice.dto.DebitResponse;
import com.badal.accountservice.exceptions.CustomException;
import com.badal.accountservice.services.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
@Slf4j
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


		if (creditRequest.getAmount() < 0.0) {
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
		log.info("delete account called");
		this.accountService.deleteAccountsById(customerId);
	}
}
