package com.nagarro.customerservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.customerservice.dto.CustomerDto;
import com.nagarro.customerservice.dto.CustomerResponse;
import com.nagarro.customerservice.services.CustomerServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
	private final CustomerServiceImpl customerService;

	// Add customer
	@PostMapping
	public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerDto customerDto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customerDto));

	}

	@GetMapping
	public ResponseEntity<CustomerDto[]> getAllCustomers() {

		return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
	}

	// Get single Customer Details
	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("id") Long customerId) {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomer(customerId));
	}

	// Update Customer Details
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCustomer(@PathVariable("id") Long customerId,
			@RequestBody CustomerDto customerDto) {
		customerService.updateCustomer(customerId, customerDto);
		return ResponseEntity.status(HttpStatus.OK).body("Customer updated!!");
	}

	// Delete Customer (Deleting customer should also delete the customer account
	// from account management service)

	public ResponseEntity<String> deleteCustomer() {
		return ResponseEntity.status(HttpStatus.OK).body("Customer deleted!!");
	}
}
