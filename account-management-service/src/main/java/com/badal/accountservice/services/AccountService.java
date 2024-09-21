package com.badal.accountservice.services;

import java.time.LocalDate;

import com.badal.accountservice.exceptions.CustomException;
import com.badal.accountservice.feign.CustomerClient;
import com.badal.accountservice.repositories.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.badal.accountservice.dto.AccountDetail;
import com.badal.accountservice.dto.AccountDto;
import com.badal.accountservice.dto.CreditRequest;
import com.badal.accountservice.dto.CreditResponse;
import com.badal.accountservice.dto.CustomerResponse;
import com.badal.accountservice.dto.DebitRequest;
import com.badal.accountservice.dto.DebitResponse;
import com.badal.accountservice.entities.Account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AccountService {
	private final ModelMapper modelMapper;
	private final AccountRepository accountRepository;
	private final CustomerClient customerClient;

	// create account
	public String addAccount(AccountDto accountDto, Long customerId) {
		try {
			Account account = new Account();

			modelMapper.map(accountDto, account);
			account.setCustomerId(customerId);
			accountRepository.save(account);

			log.info("account is {} saved", account);
			return account.getAccountNumber();

		} catch (Exception e) {
			throw new CustomException("could not create the account " + e.getMessage(), HttpStatus.BAD_REQUEST.value());
		}

	}

	// check if account exist
	public Boolean accountExist(String accountNumber) {
		return accountRepository.existsById(accountNumber);
	}

	// add money to account
	public CreditResponse addMoney(CreditRequest creditRequest) {
		try {

			// get customer
			CustomerResponse customer = this.customerClient.getCustomer(creditRequest.getCustomerId()).getBody();

			if (customer == null || !validateCredit(creditRequest, customer)) {
				throw new CustomException("Invalid credit request", HttpStatus.BAD_REQUEST.value());
			}

			Account account = this.accountRepository.findById(creditRequest.getAccountNumber())
					.orElseThrow(() -> new CustomException("Account not found", HttpStatus.BAD_REQUEST.value()));

			account.setBalance(account.getBalance() + creditRequest.getAmount());

			this.accountRepository.save(account);

			CreditResponse creditResponse = new CreditResponse(account.getAccountNumber(), creditRequest.getAmount(),
					LocalDate.now());
			return creditResponse;
		} catch (CustomException e) {

			log.error("Custom Exception occurred: {}", e.getMessage());

			throw e;
		} catch (Exception e) {
			log.error("Error processing credit request: {}", e.getMessage());

			throw new RuntimeException("Error processing credit request", e);
		}
	}

	// withdraw amount
	public DebitResponse withdrawAmount(DebitRequest debitRequest) {
		try {

			// get customer
			CustomerResponse customer = this.customerClient.getCustomer(debitRequest.getCustomerId()).getBody();

			if (customer == null || !validateDebit(debitRequest, customer)) {
				throw new CustomException("Invalid credit request", HttpStatus.BAD_REQUEST.value());
			}

			Account account = this.accountRepository.findById(debitRequest.getAccountNumber())
					.orElseThrow(() -> new CustomException("Account not found", HttpStatus.BAD_REQUEST.value()));

			if (account.getBalance() < debitRequest.getAmount()) {
				throw new CustomException("No sufficient balance", HttpStatus.BAD_REQUEST.value());
			}

			account.setBalance(account.getBalance() - debitRequest.getAmount());

			DebitResponse debitResponse = new DebitResponse(account.getAccountNumber(), debitRequest.getAmount(),
					LocalDate.now());

			return debitResponse;

		} catch (CustomException e) {

			log.error("Custom Exception occurred: {}", e.getMessage());

			throw e;
		} catch (Exception e) {
			log.error("Error processing credit request: {}", e.getMessage());

			throw new RuntimeException("Error processing credit request", e);
		}
	}

	public boolean validateCredit(CreditRequest creditRequest, CustomerResponse customer) {
		return creditRequest.getFirstName().equals(customer.getFirstName())
				&& creditRequest.getLastName().equals(customer.getLastName())
				&& creditRequest.getEmail().equals(customer.getEmail());

	}

	public boolean validateDebit(DebitRequest debitRequest, CustomerResponse customer) {
		return debitRequest.getFirstName().equals(customer.getFirstName())
				&& debitRequest.getLastName().equals(customer.getLastName())
				&& debitRequest.getEmail().equals(customer.getEmail());

	}

	public AccountDetail getAccountDetails(Long customerId, String accountNumber) {
		CustomerResponse customer = this.customerClient.getCustomer(customerId).getBody();
		Account account = this.accountRepository.findById(accountNumber)
				.orElseThrow(() -> new CustomException("Account not found", HttpStatus.BAD_REQUEST.value()));

		AccountDetail accountDetail = new AccountDetail();

		modelMapper.map(customer, accountDetail);
		modelMapper.map(account, accountDetail);

		return accountDetail;

	}

	public void deleteAccount(String accountNumber) {

		try {
			this.accountRepository.deleteById(accountNumber);
		} catch (Exception e) {
			throw new RuntimeException("something went wrong", e);
		}

	}

	public void deleteAccountsById(Long customerId) {
		try {
			this.accountRepository.deleteAccountsByCustomerId(customerId);
		} catch (Exception e) {
			throw new CustomException("Failed to delete accounts for customer with ID " + customerId, 400);
		}
	}
}
